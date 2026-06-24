plugin{
    id("com.android.application")
    id("kotlin-kapt")
}


dependencies{
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room-version")
}