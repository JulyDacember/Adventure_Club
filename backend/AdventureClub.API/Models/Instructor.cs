public class Instructor
{
    public int Id { get; set; }
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string Email { get; set; }
    public string Phone { get; set; }
    public string Specialization { get; set; }
    public int ExperienceYears { get; set; }
    public List<InstructorAssignment> InstructorAssignments { get; set; } = new();
}