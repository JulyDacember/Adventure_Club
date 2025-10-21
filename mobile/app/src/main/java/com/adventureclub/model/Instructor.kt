data class Instructor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val specialization: String,
    val experienceYears: Int,
    val instructorAssignments: List<InstructorAssignment>
)