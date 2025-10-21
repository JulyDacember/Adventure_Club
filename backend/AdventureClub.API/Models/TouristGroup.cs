public class TouristGroup
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Description { get; set; }
    public List<Tourist> Tourists { get; set; } = new();
    public List<TouristGroupRoute> TouristGroupRoutes { get; set; } = new();
}