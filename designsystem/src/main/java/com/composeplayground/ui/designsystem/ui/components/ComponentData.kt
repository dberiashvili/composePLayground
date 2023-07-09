package com.composeplayground.ui.designsystem.ui.components

data class ComponentData(
    val title: String,
    val type: ComponentType,
    val onClick: (ComponentType) -> Unit
)

enum class ComponentType {
    DEMO, LAYERS_AND_SHADOWS
}
