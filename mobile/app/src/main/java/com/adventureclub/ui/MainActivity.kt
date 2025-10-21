class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var routesAdapter: RoutesAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupObservers()
        loadData()
    }

    private fun setupUI() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.orange_dark)
        
        routesAdapter = RoutesAdapter { route ->
            showRouteDetails(route)
        }
        
        binding.routesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = routesAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        binding.fabAdd.setOnClickListener {
            showAddRouteDialog()
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_routes -> {
                    showRoutesFragment()
                    true
                }
                R.id.nav_instructors -> {
                    showInstructorsFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupObservers() {
        viewModel.routes.observe(this) { routes ->
            routesAdapter.submitList(routes)
            binding.progressBar.visibility = View.GONE
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadRoutes()
    }

    private fun showAddRouteDialog() {
        val dialog = AddRouteDialogFragment { route ->
            viewModel.addRoute(route)
        }
        dialog.show(supportFragmentManager, "AddRouteDialog")
    }

    private fun showRouteDetails(route: Route) {
        val intent = Intent(this, RouteDetailActivity::class.java).apply {
            putExtra("route_id", route.id)
        }
        startActivity(intent)
    }

    private fun showRoutesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RoutesFragment())
            .commit()
    }

    private fun showInstructorsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InstructorsFragment())
            .commit()
    }
}