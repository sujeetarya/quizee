package com.example.quizee.utils


object ColorPicker {
    private val colors = arrayOf(
        "#946656",
        "#c79274",
        "#e1b09c",
        "#ffbcda",
        "#ffd6e9",
        "#ff003f",
        "#ffbfff",
        "#7c5f59",
        "#39ca44",
        "#bb78ee",
        "#2a85ec",
        "#b10000",
        "#89cff0",
        "#00e5e5",
        "#00ffff",
        "#fa86c4",
        "#9ad0b6",
        "#e0eca5",
        "#e3a4a7",
        "#ddb372",
        "#292927",
        "#967ab3",
        "#3f3325",
        "#679d64",
        "#b88b8b",
        "#c39797",
        "#66cdaa",
        "#ddb372",
        "#dad1bf",
        "#3f3325",
        "#94dfdb",
        "#94dfdb",
        "#e9b3b6",
        "#e3a4a7",
        "#679d64",
        "#967ab3",
    )

    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}