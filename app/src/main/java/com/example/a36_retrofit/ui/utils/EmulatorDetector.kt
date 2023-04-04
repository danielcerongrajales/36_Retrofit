package com.example.a36_retrofit.ui.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*


object EmulatorDetector {
    private val QEMU_DRIVERS = arrayOf("goldfish")
    private val GENY_FILES = arrayOf(
        "/dev/socket/genyd",
        "/dev/socket/baseband_genyd"
    )
    private val PIPES = arrayOf(
        "/dev/socket/qemud",
        "/dev/qemu_pipe"
    )
    private val X86_FILES = arrayOf(
        "ueventd.android_x86.rc",
        "x86.prop",
        "ueventd.ttVM_x86.rc",
        "init.ttVM_x86.rc",
        "fstab.ttVM_x86",
        "fstab.vbox86",
        "init.vbox86.rc",
        "ueventd.vbox86.rc"
    )
    private val ANDY_FILES = arrayOf(
        "fstab.andy",
        "ueventd.andy.rc"
    )
    private val NOX_FILES = arrayOf(
        "fstab.nox",
        "init.nox.rc",
        "ueventd.nox.rc"
    )

    fun isEmulator(context: Context): Boolean {
        if (checkBasic()) return true
        if (checkAdvanced()) return true
        return checkPackageName(context)
    }

    @SuppressLint("HardwareIds")
    private fun checkBasic(): Boolean {
        var rating = 0
        if (Build.PRODUCT == "sdk_x86_64" || Build.PRODUCT == "sdk_google_phone_x86" || Build.PRODUCT == "sdk_google_phone_x86_64" || Build.PRODUCT == "sdk_google_phone_arm64" || Build.PRODUCT == "vbox86p") {
            rating++
        }
        if (Build.MANUFACTURER == "unknown") {
            rating++
        }
        if (Build.BRAND == "generic" ||
            Build.BRAND.equals(
                "android",
                ignoreCase = true
            ) || Build.BRAND == "generic_arm64" || Build.BRAND == "generic_x86" || Build.BRAND == "generic_x86_64"
        ) {
            rating++
        }
        if (Build.DEVICE == "generic" || Build.DEVICE == "generic_arm64" || Build.DEVICE == "generic_x86" || Build.DEVICE == "generic_x86_64" || Build.DEVICE == "vbox86p") {
            rating++
        }
        if (Build.MODEL == "sdk" || Build.MODEL == "Android SDK built for arm64" || Build.MODEL == "Android SDK built for armv7" || Build.MODEL == "Android SDK built for x86" || Build.MODEL == "Android SDK built for x86_64") {
            rating++
        }
        if (Build.HARDWARE == "ranchu") {
            rating++
        }
        if (Build.FINGERPRINT.contains("sdk_google_phone_arm64") ||
            Build.FINGERPRINT.contains("sdk_google_phone_armv7")
        ) {
            rating++
        }
        return b(rating)
    }

    @Suppress("DEPRECATION")
    private fun b(rating: Int): Boolean {
        var result = ((Build.FINGERPRINT.startsWith("generic")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.lowercase(Locale.getDefault()).contains("droid4x")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")) || Build.HARDWARE == "goldfish" || Build.HARDWARE == "vbox86" || Build.PRODUCT == "sdk" || Build.PRODUCT.startsWith(
            "google_sdk"
        ) || Build.PRODUCT == "sdk_x86" || Build.PRODUCT == "vbox86p" || Build.BOARD.lowercase(
            Locale.getDefault()
        ).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || Build.HARDWARE.lowercase(Locale.getDefault()).contains("nox")
                || Build.PRODUCT.lowercase(Locale.getDefault()).contains("nox")
                || Build.SERIAL.lowercase(Locale.getDefault()).contains("nox")
                || Build.HOST.contains("Droid4x-BuildStation")
                || Build.MANUFACTURER.startsWith("iToolsAVM")
                || Build.DEVICE.startsWith("iToolsAVM")
                || Build.MODEL.startsWith("iToolsAVM")
                || Build.BRAND.startsWith("generic")
                || Build.HARDWARE.startsWith("vbox86"))
        if (result) return true
        result = result or (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        if (result) return true
        result = result or ("google_sdk" == Build.PRODUCT)
        return if (result) true else rating >= 2
    }

    private fun checkQEmuDrivers(): Boolean {
        for (drivers_file in arrayOf(File("/proc/tty/drivers"), File("/proc/cpuinfo"))) {
            if (drivers_file.exists() && drivers_file.canRead()) {
                val data = ByteArray(1024)
                try {
                    val `is`: InputStream = FileInputStream(drivers_file)
                    `is`.read(data)
                    `is`.close()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                val driverData = String(data)
                for (known_qemu_driver in QEMU_DRIVERS) {
                    if (driverData.contains(known_qemu_driver)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun checkAdvanced(): Boolean {
        return (checkFiles(GENY_FILES)
                || checkFiles(ANDY_FILES)
                || checkFiles(NOX_FILES)
                || checkQEmuDrivers()
                || checkFiles(PIPES)
                || checkFiles(X86_FILES))
    }

    private fun checkFiles(targets: Array<String>): Boolean {
        for (pipe in targets) {
            val qemuFile = File(pipe)
            if (qemuFile.exists()) {
                return true
            }
        }
        return false
    }

    private fun checkPackageName(context: Context): Boolean {
        val packageManager: PackageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val availableActivities = packageManager.queryIntentActivities(intent, 0)
        for (resolveInfo in availableActivities) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.bluestacks.")) {
                return true
            }
        }
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages) {
            val packageName = packageInfo.packageName
            if (packageName.startsWith("com.vphone.")) {
                return true
            } else if (packageName.startsWith("com.bignox.")) {
                return true
            } else if (packageName.startsWith("com.nox.mopen.app")) {
                return true
            } else if (packageName.startsWith("me.haima.")) {
                return true
            } else if (packageName.startsWith("com.bluestacks.")) {
                return true
            } else if (packageName.startsWith("cn.itools.") && Build.PRODUCT.startsWith("iToolsAVM")) {
                return true
            } else if (packageName.startsWith("com.kop.")) {
                return true
            } else if (packageName.startsWith("com.kaopu.")) {
                return true
            } else if (packageName.startsWith("com.microvirt.")) {
                return true
            } else if (packageName == "com.google.android.launcher.layouts.genymotion") {
                return true
            }
        }
        val manager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val serviceInfos = manager.getRunningServices(30)
        for (serviceInfo in serviceInfos) {
            val serviceName = serviceInfo.service.className
            if (serviceName.startsWith("com.bluestacks.")) {
                return true
            }
        }
        return false
    }
}