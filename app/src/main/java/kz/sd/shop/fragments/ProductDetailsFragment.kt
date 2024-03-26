package kz.sd.shop.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentProductDetailsBinding
import kz.sd.shop.firebase.UserDao
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment: BaseFragment<FragmentProductDetailsBinding>(FragmentProductDetailsBinding::inflate) {
    private val args:ProductDetailsFragmentArgs by navArgs()
    @Inject
    lateinit var userDao: UserDao
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onBindView() {
        super.onBindView()
        val product = args.product
        with(binding){
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            title.text = product.title
            product.img?.let { imageView3.setImageResource(it) }
            price.text = product.price.toString()+" KZT"
            val bonusAmount = product.price?.times(0.05)?.toInt()
            bonus.text = "+ "+bonusAmount.toString()+ " бонусов"
            description.text = product.description

            addBtn.setOnClickListener {
                userDao.saveProductToList(product)
                showCustomDialog("Успешно" , "Добавлено в корзину")
            }
        }
    }
}