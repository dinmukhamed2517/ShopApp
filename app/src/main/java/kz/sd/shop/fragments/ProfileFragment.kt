package kz.sd.shop.fragments

import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.R
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentProfileBinding
import kz.sd.shop.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint

class ProfileFragment:BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao
    override fun onBindView() {
        userDao.getData()
        super.onBindView()
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.signOutBtn.setOnClickListener {
            signOut()
        }
        binding.email.text = firebaseAuth.currentUser?.email;


        userDao.getDataLiveData.observe(this){
            binding.name.text = it?.name
            binding.address.text = it?.address
            binding.bonus.text = it?.bonus.toString()
            if (it?.pictureUrl != null) {
                Glide.with(requireContext())
                    .load(it?.pictureUrl)
                    .into(binding.ava)
            } else {
                binding.ava.setImageResource(R.drawable.profile_icon)
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser == null){
            findNavController().navigate(R.id.action_profile_to_loginFragment)
        }

    }

    private fun signOut() {
        var alertDialog: AlertDialog? = null
        alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Выйти с аккаунта")
            .setMessage("Вы уверены что хотите выйти с аккаунта?")
            .setPositiveButton("Да") { _, _ ->
                firebaseAuth.signOut()
                alertDialog?.dismiss()
                findNavController().navigate(
                    R.id.action_profile_to_loginFragment
                )
            }
            .setNegativeButton("Отмена") { _, _ ->
                alertDialog?.dismiss()
            }
            .show()
    }


}