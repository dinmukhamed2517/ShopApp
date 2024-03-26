package kz.sd.shop.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentChoiceBinding

class ChoiceFragment:BaseFragment<FragmentChoiceBinding>(FragmentChoiceBinding::inflate) {
    private val args: ChoiceFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()


        with(binding){
            bonus.setOnClickListener {
                findNavController().navigate(ChoiceFragmentDirections.actionChoiceFragmentToBonusPaymentFragment(args.total))
            }
            card.setOnClickListener {
                findNavController().navigate(ChoiceFragmentDirections.actionChoiceFragmentToPaymentFragment(args.total))
            }
        }
    }

}