package com.example.plants

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso

private const val IMG = "IMG"
private const val NAME = "NAME"

class ImageFragment : DialogFragment() {
    private var param: Int? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getInt(IMG)
            name = it.getString(NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_image, container, false)

        rootView.findViewById<ImageView>(R.id.mBack).setOnClickListener {
            dialog?.dismiss()
        }

        val mName = rootView.findViewById<TextView>(R.id.mName)
        val mImage = rootView.findViewById<ImageView>(R.id.mImage)
        mName.setText(name)
        Picasso.get().load(param!!).centerCrop().fit().into(mImage)
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param: Int, name: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMG, param)
                    putString(NAME, name)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = getDialog()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }
}