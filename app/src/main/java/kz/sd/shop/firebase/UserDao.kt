package kz.sd.shop.firebase


import com.google.firebase.auth.FirebaseAuth
import kz.sd.shop.models.User

class UserDao(
    private var firebaseAuth:FirebaseAuth
):FRDBWrapper<User>(){
    override fun getTableName(): String {
        return firebaseAuth?.currentUser?.uid.toString()
    }
    override fun getClassType(): Class<User> {
        return User::class.java
    }
}