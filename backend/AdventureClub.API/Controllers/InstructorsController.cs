[ApiController]
[Route("api/[controller]")]
public class InstructorsController : ControllerBase
{
    private readonly AdventureClubContext _context;

    public InstructorsController(AdventureClubContext context)
    {
        _context = context;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Instructor>>> GetInstructors()
    {
        return await _context.Instructors
            .Include(i => i.InstructorAssignments)
            .ThenInclude(ia => ia.Route)
            .Include(i => i.InstructorAssignments)
            .ThenInclude(ia => ia.TouristGroup)
            .ToListAsync();
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Instructor>> GetInstructor(int id)
    {
        var instructor = await _context.Instructors
            .Include(i => i.InstructorAssignments)
            .ThenInclude(ia => ia.Route)
            .Include(i => i.InstructorAssignments)
            .ThenInclude(ia => ia.TouristGroup)
            .FirstOrDefaultAsync(i => i.Id == id);

        if (instructor == null) return NotFound();
        return instructor;
    }

    [HttpPost]
    public async Task<ActionResult<Instructor>> PostInstructor(Instructor instructor)
    {
        _context.Instructors.Add(instructor);
        await _context.SaveChangesAsync();
        return CreatedAtAction(nameof(GetInstructor), new { id = instructor.Id }, instructor);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteInstructor(int id)
    {
        var instructor = await _context.Instructors.FindAsync(id);
        if (instructor == null) return NotFound();
        _context.Instructors.Remove(instructor);
        await _context.SaveChangesAsync();
        return NoContent();
    }
}