package kz.sd.shop.fragments


import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.R
import kz.sd.shop.adapters.CartAdapter
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentCartBinding
import kz.sd.shop.firebase.UserDao
import kz.sd.shop.models.Product
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    var products: MutableList<Product> = mutableListOf()

    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var userDao: UserDao
    var totalCost:Double = 0.0
    override fun onBindView() {
        userDao.getData()
        super.onBindView()

        val adapter = CartAdapter()

        adapter.itemClick = {
            findNavController().navigate(
                CartFragmentDirections.actionCartToProductDetailsFragment(it)
            )
        }
        binding.addBtn.setOnClickListener {
            findNavController().navigate(
                CartFragmentDirections.actionCartToChoiceFragment(totalCost.toFloat())
            )
        }
        with(binding){
            startBtn.setOnClickListener {
                findNavController().navigate(R.id.action_cart_to_catalog)
            }

            cartRecycler.adapter = adapter
            cartRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            adapter.deleteButtonClicked = { product ->
                val keyToDelete = userDao.getDataLiveData.value?.cart?.filterValues { it.id == product.id }?.keys?.firstOrNull()
                keyToDelete?.let { key ->
                    userDao.deleteProductFromList(key)
                    val updatedProducts = ArrayList(products).apply {
                        remove(product)
                    }
                    adapter.submitList(updatedProducts){
                        updateTotalAndBonuses()
                    }
                    products = updatedProducts
                } ?: run {
                    Log.e("CartFragment", "Failed to find key for product deletion")
                }
            }
        }
        userDao.getDataLiveData.observe(viewLifecycleOwner) { userData ->
            products.clear()
            userData?.cart?.values?.let { productList ->
                products.addAll(productList)
                updateTotalAndBonuses()
            }
            adapter.submitList(products.toList())

            val isCartEmpty = products.isEmpty()
            binding.emptyCartCv.isVisible = isCartEmpty
            binding.cartRecycler.isVisible = !isCartEmpty
            binding.total.isVisible = !isCartEmpty
            binding.addBtn.isVisible = !isCartEmpty
        }
    }
    private fun updateTotalAndBonuses() {
        totalCost = products.sumOf { it.price ?: 0.0 }
        val totalBonuses = (totalCost!! * 0.05).toInt()

        with(binding) {
            total.isVisible = products.isNotEmpty()
            totalPrice.text = "${totalCost} KZT"
            if(products.size == 1){
                amount.text = "1 товар на сумму"
            }
            else{
                amount.text = products.size.toString()+" товаров на сумму"
            }
            bonuses.text = "+$totalBonuses"
        }
    }
}