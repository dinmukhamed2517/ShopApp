package kz.sd.shop.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.R
import kz.sd.shop.adapters.ProductAdapter
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentHomeBinding
import kz.sd.shop.models.Product


@AndroidEntryPoint

class HomeFragment:BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun onBindView() {
        super.onBindView()

        var adapter1 = ProductAdapter()
        var adapter2 = ProductAdapter()
        binding.searchBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_to_searchFragment
            )
        }
        binding.recycler1.adapter = adapter1
        binding.recycler1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recycler2.adapter = adapter2
        binding.recycler2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter1.submitList(getList())
        adapter2.submitList(getList2())


        adapter1.itemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToProductDetailsFragment(
                    it
                )
            )
        }
        adapter2.itemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToProductDetailsFragment(
                    it
                )
            )
        }
    }

    fun getList(): List<Product> {
        return listOf(
            Product(
                1,
                "Бананы кг",
                R.drawable.product_1,
                900.0,
                "Бананы десертные обладают плотной, сладкой мякотью и освежающим ароматом с травянистыми нотками. Цвет бананов может варьироваться от салатного до желтого цвета."
            ),
            Product(
                2,
                "Кефир Фуд Мастер 2,5% т/п 1 л",
                R.drawable.product_2,
                700.0,
                "Натуральный кисломолочный напиток, изготавливается из цельного или обезжиренного молока. Уникальные и целебные свойства кефира известны давно, этот напиток оказывает благоприятное воздействие на органы пищеварения и оздоравливает весь организм."
            ),
            Product(
                3,
                "Сок Gracio зеленое яблоко 950 мл",
                R.drawable.product_3,
                900.0,
                "Яблочный сок с сахаром. Без консервантов, пастеризованный, восстановленный. Сок Gracio Яблоко обладает ярким ароматом спелого яблока с насыщенными кисловато-сладкими нотками. Его утонченный и легкий вкус наполнит Вас бодростью и подарит заряд энергии на целый день."
            ),
            Product(
                4,
                "Орех грецкий очищенный Arbuz Select 300 г",
                R.drawable.product_4,
                2030.0,
                "Наши грецкие орехи, тщательно отобраны и обработаны, сохранили свежесть и все полезные свойства. Грецкий орех богат полезными жирами, белком, витаминами, минералами и антиоксидантами, что способствует улучшению здоровья сердца, мозга, костей."
            ),
            Product(
                5,
                "Томаты на ветке кг",
                R.drawable.product_5,
                1090.0,
                "Помидоры богаты витамином С, калием, фолатами  витамином К. Однако больше всего томаты ценны тем, что они являются основным источником мощного антиоксиданта ликопина, который снижает риск сердечных заболеваний и рака. Витамин С. Важное питательное вещество и антиоксидант."
            ),
            Product(
                6,
                "Сыр Ламбер полутвердый 50% 0.5-0.6 кг",
                R.drawable.product_6,
                3500.0,
                "Традиционный Сыр Ламбер, производится из уникального по своим качествам коровьего молока Алтайского края. Этот сыр относится к полутвердым сычужным сортам сыров."
            ),
            Product(
                7,
                "Напиток энергетический Red Bull 355 мл",
                R.drawable.product_7,
                1070.0,
                "Red Bull - это безалкогольный функциональный газированный напиток, который окрыляет, когда это особенно необходимо. Помогает ведущим спортсменам, студентам, представителям экстремальных профессий, а также во время длительных автомобильных поездок."
            ),
        )
    }

    fun getList2():List<Product>{
       return listOf(
           Product(
               8,
               "Пастрами Jaqsi Meat из индейки 165 г",
               R.drawable.product_8,
               2500.0,
               "Премиальный низкокалорийный мясной деликатес из грудки индейки. 100% натуральный продукт, Халяль, не содержит ГМО. Варёно-копченный, готовый к употреблению. Отлично подходит для приготовления завтраков, сэндвичей, пиццы, мясных нарезок, салатов и супов."
           ),
           Product(
               9,
               "Пастрами Jaqsi Meat 165 г",
               R.drawable.product_9,
               3300.0,
               "Премиальный мясной деликатес из мраморной говядины породы Black Angus. 100% натуральный продукт, Халяль, не содержит ГМО. Варёно‐копченный, готовый к употреблению. Отлично подходит для приготовления завтраков, сэндвичей, пиццы, мясных нарезок, салатов и супов."
           ),
           Product(
               10,
               "Мякоть Meta Farm говядина кг",
               R.drawable.product_10,
               9900.0,
               "Охлажденная мякоть говядины — идеальный продукт для приготовления всевозможных блюд. Её отваривают, жарят, тушат, запекают и даже используют для приготовления копченых мясных деликатесов. Структура мелко волокнистая, обладает уникальными гастрономическими свойствами. В процессе приготовления проста, не требует особых условий, достигает готовности за краткий промежуток времени."
           ),
           Product(
               11,
               "Колбаса Папа может филейная 500 г",
               R.drawable.product_11,
               1200.0,
               "Вареная колбаса «Филейная» делается из мяса птицы. Для ее изготовления используются куриные грудки, поэтому продукт получается вкусным, нежным, а также легко усваивается."
           ),
           Product(
               12,
               "Колбаса Папа может сервелат",
               R.drawable.product_12,
               1660.0,
               "Колбаса Папа Может Сервелат Финский варено-копченая, приготовлена по оригинальной рецептуре Останкинского мясоперерабатывающего завода из свинины и шпика с добавлением ароматных пряностей: мускатного ореха и семян горчицы."
           ),
           Product(
               13,
               "Нарезка Черкизово",
               R.drawable.product_13,
               1100.0,
               ""
           ),
           Product(
               14,
               "Колбаса Мираторг Молочная вареная 470 г",
               R.drawable.product_8,
               1700.0,
               "Колбаса «Молочная» в полиамидной оболочке – это нежная колбаса с молочным вкусом. Благодаря непроницаемой оболочке такую вареную колбасу отличает большой срок годности и высокая стойкость к воздействию неблагоприятных факторов. Оригинальный вкус и аромат колбасы достигаются благодаря использованию молока и специй, регламентируемых ГОСТ."
           ),
       )
    }
}