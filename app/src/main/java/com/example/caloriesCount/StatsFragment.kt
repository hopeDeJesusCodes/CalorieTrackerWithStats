import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.caloriesCount.ItemViewModel
import com.example.caloriesCount.R


class StatsFragment : Fragment() {
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stats_page, container, false)

        // Initialize UI elements and handle statistics here
        val highestCaloriesTextView = view.findViewById<TextView>(R.id.highestCaloriesTextView)
        val lowestCaloriesTextView = view.findViewById<TextView>(R.id.lowestCaloriesTextView)
        val avgCaloriesTextView = view.findViewById<TextView>(R.id.averageCaloriesTextView)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        // Observe LiveData for highest, lowest, and average calories
        itemViewModel.getMaxCalories().observe(viewLifecycleOwner) { maxCalories ->
            highestCaloriesTextView.text = "Highest Calories: $maxCalories"
        }

        itemViewModel.getMinCalories().observe(viewLifecycleOwner) { minCalories ->
            lowestCaloriesTextView.text = "Lowest Calories: $minCalories"
        }

        itemViewModel.getAvgCalories().observe(viewLifecycleOwner) { avgCalories ->
            avgCaloriesTextView.text = "Average Calories: ${String.format("%.2f", avgCalories)}"
        }

        return view
    }
}


