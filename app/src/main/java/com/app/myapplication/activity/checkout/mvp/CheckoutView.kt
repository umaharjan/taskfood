package com.app.myapplication.activity.checkout.mvp

import android.app.ProgressDialog
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.R
import com.app.myapplication.apputils.AppConstant
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.vicmikhailau.maskededittext.MaskedFormatter
import com.vicmikhailau.maskededittext.MaskedWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.checkout_layout.view.*
import java.util.*
import java.util.concurrent.TimeUnit

class CheckoutView(private val appCompatActivity: AppCompatActivity) :
    FrameLayout(appCompatActivity) {
    var cardTypeList: ArrayList<String>?=null
    var isdebitValid: Boolean=false
    var isExpireValid: Boolean=false
    var isSecurityValid: Boolean=false
    var isAccountValid: Boolean=false
    var isCardNameValid: Boolean=false
    var radioaSelectdType: Int=1
    private var formatter: MaskedFormatter?=null
    private var debitformatter: MaskedFormatter?=null
    lateinit var _calendar: Calendar
    var year: Int=0
    private val progressDialog=ProgressDialog(context, R.style.MyProgressDialogStyle)

    init {
        View.inflate(appCompatActivity, R.layout.checkout_layout, this)
        cardTypeList=ArrayList()
        try {
            setMask(AppConstant.DEBITCARD)
        } catch (ex: Exception) {

        }
        _calendar=Calendar.getInstance()
        year=_calendar.get(Calendar.YEAR)
        getSecurityCodeListner()
        getCardListner()
        getExpiredateListner()
        getBtnEnable(false)
        onClickRadio()
        onClickRadioBanking()
        getAccountListner()
        getCardNameListner()
        setExpireMask(AppConstant.MMYY)
    }


    fun showProgressDialog() {
        progressDialog.show()
        progressDialog.setMessage("Processing")
    }

    fun hidProgressDialog() {
        progressDialog.dismiss()
    }

    fun getDebitCardNumber(): String {
        return etDebit.text.toString()
    }


    fun getExpireDate(): String {
        return etDDMM.text.toString()
    }

    fun getSecurityCode(): String {
        return etSecurityCode.text.toString()
    }

    fun getAddressNumber(): String {
        return etAddress.text.toString()
    }

    fun getCardName(): String {
        return etCardName.text.toString()
    }

    fun onClickRadio() {
        radioSelected.setOnClickListener {
            radioSelected.setBackgroundResource(R.drawable.selectedradio)
            radioSelected.isChecked=true
            radioNetBanking.setBackgroundResource(R.drawable.unselectedradio)
            radioNetBanking.isChecked=false

            radioaSelectdType=1
        }
    }

    fun onClickRadioBanking() {
        radioNetBanking.setOnClickListener {
            radioNetBanking.setBackgroundResource(R.drawable.selectedradio)
            radioNetBanking.isChecked=true
            radioSelected.setBackgroundResource(R.drawable.unselectedradio)
            radioSelected.isChecked=false
            radioaSelectdType=2
        }
    }

    private fun setMask(mask: String) {
        debitformatter=MaskedFormatter(mask)
        try {
            etDebit.addTextChangedListener(MaskedWatcher(debitformatter, etDebit))
        } catch (ex: Exception) {

        }
    }

    private fun setExpireMask(mask: String) {
        formatter=MaskedFormatter(mask)
        etDDMM.addTextChangedListener(MaskedWatcher(formatter, etDDMM))
    }

    private fun getCardListner() {
        RxTextView.textChanges(etDebit).skip(1)
            .map(CharSequence::toString)
            .debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                when {
                    AppConstant.getCradNUmber(getDebitCardNumber()).length == 4 -> {
                        for (i in getCardArray()) {
                            if (AppConstant.getCradNUmber(getDebitCardNumber())
                                    .matches(i.toRegex())
                            ) {
                                if (AppConstant.getCradNUmber(getDebitCardNumber())
                                        .startsWith("4")
                                ) {
                                    ivCard.setBackgroundResource(R.drawable.ic_visa)
                                } else if (AppConstant.getCradNUmber(getDebitCardNumber())
                                        .startsWith("5")
                                ) {
                                    ivCard.setBackgroundResource(R.drawable.ic_mastercard)
                                } else if (AppConstant.getCradNUmber(getDebitCardNumber())
                                        .startsWith("6")
                                ) {
                                    ivCard.setBackgroundResource(R.drawable.ic_discover)
                                } else if (AppConstant.getCradNUmber(getDebitCardNumber())
                                        .startsWith("37")
                                ) {
                                    ivCard.setBackgroundResource(R.drawable.ic_american)
                                }
                            }
                        }
                    }

                    AppConstant.getCradNUmber(getDebitCardNumber()).length == 16 -> {
                        isdebitValid=true
                        when {
                            isValid() -> getBtnEnable(true)
                        }
                    }
                    AppConstant.getCradNUmber(getDebitCardNumber()).length != 16 -> {
                        isdebitValid=false
                        getBtnEnable(false)
                    }

                }

            }.subscribe()
    }

    private fun getSecurityCodeListner() {
        RxTextView.textChanges(etSecurityCode).skip(1)
            .map(CharSequence::toString)
            .debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                when {
                    getSecurityCode().length > 2 -> {
                        isSecurityValid=true
                        when {
                            isValid() -> getBtnEnable(true)
                        }
                    }
                    else -> {
                        isSecurityValid=false
                        getBtnEnable(false)
                    }
                }

            }.subscribe()
    }

    private fun getExpiredateListner() {
        RxTextView.textChanges(etDDMM).skip(1)
            .map(CharSequence::toString)
            .debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                when {
                    getExpireDate().length == 1 -> {
                        when {
                            AppConstant.getSubstring(getExpireDate()) > 1 -> {
                                etDDMM.setText("0" + getExpireDate() + "/")
                            }
                        }
                    }
                    getExpireDate().length == 2 -> {
                        when {
                            getExpireDate().toInt() >= 13 -> {
                                etDDMM.setText("12/")
                            }
                            getExpireDate().toInt() == 0 -> etDDMM.setText("01/")
                            else -> etDDMM.setText(getExpireDate() + "/")
                        }
                    }
                }

                when {
                    getExpireDate().length == 5 -> {
                        isExpireValid=true
                        when {
                            isValid() -> getBtnEnable(true)
                        }
                    }
                    else -> {
                        isExpireValid=false
                        getBtnEnable(false)
                    }
                }

            }.subscribe()
    }


    private fun getAccountListner() {
        RxTextView.textChanges(etAddress).skip(1)
            .map(CharSequence::toString)
            .debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                when {
                    getAddressNumber().isNotEmpty() -> {
                        isAccountValid=true
                        when {
                            isValid() -> getBtnEnable(true)
                        }
                    }
                    else -> {
                        isAccountValid=false
                        getBtnEnable(false)
                    }
                }

            }.subscribe()
    }

    private fun getCardNameListner() {
        RxTextView.textChanges(etCardName).skip(1)
            .map(CharSequence::toString)
            .debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                when {
                    getCardName().isNotEmpty() -> {
                        isCardNameValid=true
                        when {
                            isValid() -> getBtnEnable(true)
                        }
                    }
                    else -> {
                        isCardNameValid=false
                        getBtnEnable(false)
                    }
                }

            }.subscribe()
    }

    fun isValid(): Boolean {
        when {
            isdebitValid && isSecurityValid && isExpireValid && isAccountValid && isCardNameValid -> {
                return true
            }
            else -> return false
        }
    }

    fun getBtnEnable(aboolena: Boolean) {
        when {
            aboolena -> {
                btnCheckOut.isEnabled=true

            }
            else -> {

                btnCheckOut.isEnabled=false

            }
        }
    }

    fun getCardArray(): ArrayList<String> {
        val ptVisa="^4[0-9]{6,}$"
        cardTypeList!!.add(ptVisa)
        val ptMasterCard="^5[1-5][0-9]{5,}$"
        cardTypeList!!.add(ptMasterCard)
        val ptAmeExp="^3[47][0-9]{5,}$"
        cardTypeList!!.add(ptAmeExp)
        val ptDinClb="^3(?:0[0-5]|[68][0-9])[0-9]{4,}$"
        cardTypeList!!.add(ptDinClb)
        val ptDiscover="^6(?:011|5[0-9]{2})[0-9]{3,}$"
        cardTypeList!!.add(ptDiscover)
        val ptJcb="^(?:2131|1800|35[0-9]{3})[0-9]{3,}$"
        cardTypeList!!.add(ptJcb)
        return cardTypeList!!
    }

    fun getCheckObserable(): Observable<Any> {
        return RxView.clicks(btnCheckOut)

    }

    fun setSummary(price: Double, discount: Double) {
        appCompatActivity.runOnUiThread(Runnable {
            tvDiscount.text="Rs. ${discount}"
            tvSubTotal.text="Rs. ${price}"
            tvShippingCharge.text="Rs 100"
            var totalprice=price + 100 - discount
            tvTotal.text="Rs.${totalprice}"
        })
    }
fun getBackObserable():Observable<Any>{
    return  RxView.clicks(ivBack)
}

}