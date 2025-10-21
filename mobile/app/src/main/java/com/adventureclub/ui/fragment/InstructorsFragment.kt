class InstructorsFragment : Fragment() {
    private var _binding: FragmentInstructorsBinding? = null
    private val binding get() = _binding!!
    private lateinit var instructorsAdapter: InstructorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, 
        container: ViewGroup?, 
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadInstructors()
    }

    private fun setupRecyclerView() {
        instructorsAdapter = InstructorsAdapter { instructor ->
            showInstructorProfile(instructor)
        }
        
        binding.instructorsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = instructorsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun loadInstructors() {
        binding.progressBar.visibility = View.VISIBLE
        
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getInstructors()
                if (response.isSuccessful) {
                    instructorsAdapter.submitList(response.body())
                } else {
                    Toast.makeText(requireContext(), "Ошибка загрузки инструкторов", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка сети: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun showInstructorProfile(instructor: Instructor) {
        val dialog = InstructorProfileDialogFragment(instructor)
        dialog.show(parentFragmentManager, "InstructorProfile")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}