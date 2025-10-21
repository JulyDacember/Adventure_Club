public class Route
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Description { get; set; }
    public string Difficulty { get; set; }
    public decimal Price { get; set; }
    public List<TouristRoute> TouristRoutes { get; set; } = new();
    public List<TouristGroupRoute> TouristGroupRoutes { get; set; } = new();
    public List<InstructorAssignment> InstructorAssignments { get; set; } = new();
}