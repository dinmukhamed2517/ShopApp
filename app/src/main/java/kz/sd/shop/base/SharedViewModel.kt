package kz.sd.shop.base


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel(){
    lateinit var name: String
    lateinit var lastname: String
    lateinit var address:String
}