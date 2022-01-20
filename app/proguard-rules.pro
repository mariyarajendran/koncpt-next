# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
##
#-keep class  us.zoom.**{*;}
#-keep class  com.zipow.**{*;}
#-keep class  us.zipow.**{*;}
#-keep class  org.webrtc.**{*;}
#-keep class  us.google.protobuf.**{*;}
#-dontwarn us.zoom.**
#-dontwarn com.zipow.**
#
#
#-optimizationpasses 5
#-dump class_files.txt
#-printseeds seeds.txt
#-printusage unused.txt
#-printmapping mapping.txt
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging*/
#-allowaccessmodification
#-repackageclasses ''
#

#
#
#    ## keep Enum in Response Objects
#-keepclassmembers enum com.android.services.*.* { *; }
##
##
#    ## Note not be needed unless some model classes don't implement Serializable interface
#    ## Keep model classes used by ORMlite
#    -keep class com.android.model.*.*
#
#
#    ## keep classes and class members that implement java.io.Serializable from being removed or renamed
#    ## Fixes "Class class com.twinpeek.android.model.User does not have an id field" execption
#    -keep class * implements java.io.Serializable {
#        *;
#    }
#
#    ## Rules for Retrofit2
#    -keepclasseswithmembers class * {
#        @retrofit2.* <methods>;
#    }
#
#    -keepclasseswithmembers interface * {
#        @retrofit2.* <methods>;
#    }
#
#    -keepclasseswithmembers class * {
#        @retrofit2.http.* <methods>;
#    }
#
#    -keepclasseswithmembers interface * {
#        @retrofit2.http.* <methods>;
#    }
#
##
##
##    # Platform calls Class.forName on types which do not exist on Android to determine platform.
##    -dontnote retrofit2.Platform
##    # Platform used when running on RoboVM on iOS. Will not be used at runtime.
##    -dontnote retrofit2.Platform$IOS$MainThreadExecutor
##    # Platform used when running on Java 8 VMs. Will not be used at runtime.
##    -dontwarn retrofit2.Platform$Java8
##    # Retain generic type information for use by reflection by converters and adapters.
##    -keepattributes Signature
##    # Retain declared checked exceptions for use by a Proxy instance.
##    -keepattributes Exceptions
##
##
##    ## Rules for Gson
##    # For using GSON @Expose annotation
##    -keepattributes *Annotation*
##    # Gson specific classes
##    -keep class sun.misc.Unsafe.* { *; }
##    -keep class com.google.gson.stream.*.* { *; }
##
##    # Prevent proguard from stripping interface information from TypeAdapterFactory,
##    # JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
##    -keep class * implements com.google.gson.TypeAdapterFactory
##    -keep class * implements com.google.gson.JsonSerializer
##    -keep class * implements com.google.gson.JsonDeserializer
##
##
##
#
#

#
#
-keep class androidx.appcompat.widget.**{
*;
}

-keepclassmembers class app.technotech.koncpt.**{
*;
}

-keep class com.facebook.** {
   *;
}


-ignorewarnings
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-dontwarn okhttp3.**
-keep class okhttp3.** {*;}

-keep class dmax.dialog.** { *;}
-keep class com.shockwave.**




#
-dontwarn rx.**
#-dontwarn okio.**
#
#-dontwarn com.squareup.okhttp.**
#-keep class com.squareup.okhttp.** { *; }
#-keep interface com.squareup.okhttp.** { *; }
#
#-dontwarn javax.annotation.Nullable
#-dontwarn javax.annotation.ParametersAreNonnullByDefault
#
#-dontwarn javax.annotation.**
#-dontwarn retrofit2.Platform$Java8

#-------------------------------------------------------------------------------------------------------------------------------------------

# Retrofit 2.X
## https://square.github.io/retrofit/ ##
# https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-retrofit2.pro



#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#



#-------------------------------------------------------------------------------------------------------------------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn okio.**

#-------------------------------------------------------------------------------------------------------------------------------------------

#-keepattributes InnerClasses
#-keepattributes EnclosingMethod

#-------------------------------------------------------------------------------------------------------------------------------------------

# remove logs
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

#-------------------------------------------------------------------------------------------------------------------------------------------
