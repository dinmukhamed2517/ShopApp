package kz.sd.shop.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentBonusPaymentBinding
import kz.sd.shop.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint
class BonusPaymentFragment:BaseFragment<FragmentBonusPaymentBinding>(FragmentBonusPaymentBinding::inflate) {
    private val args:BonusPaymentFragmentArgs by navArgs()
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var userDao: UserDao
    private var bonus:Float? = null

    override fun onBindView() {
        userDao.getData()
        super.onBindView()

        userDao.getDataLiveData.observe(this){
            bonus = it?.bonus
            binding.bonus.text = it?.bonus.toString()
        }
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.total.text = args.total.toString()+" KZT"
        binding.confirmBtn.setOnClickListener {
            if(bonus!! >= args.total){
                userDao.saveBonus(bonus!! - args.total);
                userDao.clearCart()
                showCustomDialog("Поздравляю!", "Вы купили товаров на "+args.total.toString()+" KZT")
                findNavController().navigate(
                    BonusPaymentFragmentDirections.actionBonusPaymentFragmentToHome()
                )
            }
            else{
                showCustomCancelDialog("Ой!", "У вас недостаточно бонусов для оплаты")
            }

        }
        binding.self.setOnClickListener {
            binding.self.isChecked = true
            binding.delivery.isChecked = false
        }
        binding.delivery.setOnClickListener {
            binding.self.isChecked = false
            binding.delivery.isChecked = true
        }
    }

}