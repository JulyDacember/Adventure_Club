data class InstructorAssignment(
    val id: Int,
    val instructorId: Int,
    val routeId: Int,
    val touristGroupId: Int,
    val assignmentDate: String,
    val route: Route? = null,
    val touristGroup: TouristGroup? = null
)