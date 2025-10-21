public class AdventureClubContext : DbContext
{
    public AdventureClubContext(DbContextOptions<AdventureClubContext> options) : base(options) { }

    public DbSet<Tourist> Tourists { get; set; }
    public DbSet<TouristGroup> TouristGroups { get; set; }
    public DbSet<Route> Routes { get; set; }
    public DbSet<Instructor> Instructors { get; set; }
    public DbSet<TouristRoute> TouristRoutes { get; set; }
    public DbSet<TouristGroupRoute> TouristGroupRoutes { get; set; }
    public DbSet<InstructorAssignment> InstructorAssignments { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        // Конфигурация многих-ко-многим для туристов и маршрутов
        modelBuilder.Entity<TouristRoute>()
            .HasKey(tr => new { tr.TouristId, tr.RouteId });

        modelBuilder.Entity<TouristRoute>()
            .HasOne(tr => tr.Tourist)
            .WithMany(t => t.TouristRoutes)
            .HasForeignKey(tr => tr.TouristId);

        modelBuilder.Entity<TouristRoute>()
            .HasOne(tr => tr.Route)
            .WithMany(r => r.TouristRoutes)
            .HasForeignKey(tr => tr.RouteId);

        // Конфигурация многих-ко-многим для групп и маршрутов
        modelBuilder.Entity<TouristGroupRoute>()
            .HasKey(tgr => new { tgr.TouristGroupId, tgr.RouteId });

        modelBuilder.Entity<TouristGroupRoute>()
            .HasOne(tgr => tgr.TouristGroup)
            .WithMany(tg => tg.TouristGroupRoutes)
            .HasForeignKey(tgr => tgr.TouristGroupId);

        modelBuilder.Entity<TouristGroupRoute>()
            .HasOne(tgr => tgr.Route)
            .WithMany(r => r.TouristGroupRoutes)
            .HasForeignKey(tgr => tgr.RouteId);
    }
}