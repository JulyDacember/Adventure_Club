public class Tourist
{
    public int Id { get; set; }
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string Email { get; set; }
    public string Phone { get; set; }
    public DateTime DateOfBirth { get; set; }
    public List<TouristGroup> TouristGroups { get; set; } = new();
    public List<TouristRoute> TouristRoutes { get; set; } = new();
}