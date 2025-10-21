class RoutesAdapter(private val onItemClick: (Route) -> Unit) : 
    ListAdapter<Route, RoutesAdapter.RouteViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val binding = ItemRouteBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return RouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = getItem(position)
        holder.bind(route)
    }

    inner class RouteViewHolder(private val binding: ItemRouteBinding) : 
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(adapterPosition))
            }
        }

        fun bind(route: Route) {
            binding.apply {
                tvRouteName.text = route.name
                tvRouteDescription.text = route.description
                tvDifficulty.text = route.difficulty
                tvPrice.text = "₽${route.price}"

                val difficultyColor = when (route.difficulty.toLowerCase()) {
                    "легкий" -> R.color.green
                    "средний" -> R.color.orange
                    "сложный" -> R.color.red
                    else -> R.color.gray
                }
                tvDifficulty.setTextColor(ContextCompat.getColor(root.context, difficultyColor))
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Route>() {
        override fun areItemsTheSame(oldItem: Route, newItem: Route): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Route, newItem: Route): Boolean {
            return oldItem == newItem
        }
    }
}