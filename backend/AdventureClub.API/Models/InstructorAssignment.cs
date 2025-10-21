public class InstructorAssignment
{
    public int Id { get; set; }
    public int InstructorId { get; set; }
    public Instructor Instructor { get; set; }
    public int RouteId { get; set; }
    public Route Route { get; set; }
    public int TouristGroupId { get; set; }
    public TouristGroup TouristGroup { get; set; }
    public DateTime AssignmentDate { get; set; }
}