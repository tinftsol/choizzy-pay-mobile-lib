package io.choizzy.pay

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.choizzy.pay_lib.ChoizzyPayActivity
import io.choizzy.pay_lib.SignAndSendTransactions

class MainActivity : Activity(), SignAndSendTransactions {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(
            ChoizzyPayActivity.intent(
                this,
                "AkvbwvJMxtHPXLDB1fcKS49iLvx2veRqC5eeAySRSfyg",
                this
            )
        )
    }

    // Please provide your own implementation of SignAndSendTransactions to the ChoizzyPayActivity
    // You have to signAndSendTransactions on your side, using the wallet of your choice or
    // mobile-wallet-adapter, thank you!
    // For any feedbacks or questions, please reach out to us via email: choizzio@gmail.com or twitter: @choizzy_io
    override fun signAndSendTransactions(transactions: List<IntArray>): Boolean {
        Log.d("MainActivity", "signAndSendTransactions: $transactions")

        return true
    }
}