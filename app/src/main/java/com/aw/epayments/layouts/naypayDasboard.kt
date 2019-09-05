package com.aw.epayments.layouts

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.method.LinkMovementMethod
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.Toast


import com.aw.epayments.Adapters.ImageData
import com.aw.epayments.Adapters.MenuAdapter
import com.aw.epayments.Adapters.SubMenuAdapter

import com.aw.epayments.R

import com.aw.epayments.fragments.LoginFragment
import com.aw.epayments.activity.Recipts
import com.aw.epayments.api.Const
import com.aw.epayments.fragments.HouseRentFragment
import com.aw.epayments.fragments.LandRateFragment
import com.aw.epayments.api.Api

import com.aw.epayments.fragments.DailyParkingFragment

import com.aw.epayments.fragments.MiscellaneousFragment
import com.aw.epayments.fragments.ParkingPenalties
import com.aw.epayments.fragments.ParkingStatus
import com.aw.epayments.models.Response.Payment
import com.aw.epayments.models.Response.PaymentsResponse
import com.aw.epayments.models.Response.TransactionStatusResponse

import java.util.ArrayList
import java.util.HashMap


import com.github.javiersantos.appupdater.AppUpdater
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.naypay_dasboard.*
import kotlinx.android.synthetic.main.second_dashboard.*
import kotlinx.android.synthetic.main.ubp_link.view.*


class naypayDasboard : AppCompatActivity(),
        MenuAdapter.ItemClickListener,
        SubMenuAdapter.ItemClickListener
         {
    internal var current = 0

    private var showMenu = false

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.naypay_dasboard)
        try {
            dashBoardSLiderOffline()
            val menuImages = ArrayList<Int>()
            val parking_icon = this.resources.getIdentifier("parking", "drawable", this.packageName)

            val house_rent = this.resources.getIdentifier("rent", "drawable", this.packageName)
            val land_rates = this.resources.getIdentifier("landrate", "drawable", this.packageName)
            val ubp = this.resources.getIdentifier("business", "drawable", this.packageName)
            val penalty = this.resources.getIdentifier("penalties", "drawable", this.packageName)
            val bill = this.resources.getIdentifier("bill", "drawable", this.packageName)
            val advertisements = this.resources.getIdentifier("panish", "drawable", this.packageName)
            val penalties = this.resources.getIdentifier("add_icon", "drawable", this.packageName)
            val markets = this.resources.getIdentifier("stall", "drawable", this.packageName)

            menuImages.add(parking_icon)
            menuImages.add(house_rent)
            menuImages.add(land_rates)
            menuImages.add(ubp)
            menuImages.add(penalty)
            menuImages.add(bill)
            menuImages.add(advertisements)
            menuImages.add(penalties)
            menuImages.add(markets)

            val icon_title = ArrayList<String>()
            icon_title.add("Parking")

            icon_title.add("House Rent")
            icon_title.add("Land Rates")
            icon_title.add("UBP")
            icon_title.add("Parking Status")
            icon_title.add("Bill")
            icon_title.add("Penalties")
            icon_title.add("Adverts Coming Soon")
            icon_title.add("Stalls Coming Soon")




            login_button.setOnClickListener {
                var loginFragment = LoginFragment()
                LoginFragment.isLogin = true
                loginFragment.show(fragmentManager, loginFragment.tag)
            }
            //dashBoardSLiderOffline();
            sign_up_button.setOnClickListener {
                var loginFragment = LoginFragment()
                LoginFragment.isLogin = false
                loginFragment.show(fragmentManager, loginFragment.tag)
            }
            if (Api.getValue(this@naypayDasboard, "LoginStatus") == "login") {
                access.visibility = View.GONE
                //logoutItem.setVisible(true);
                val names = Api.getValue(this@naypayDasboard, "FullNames")!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                user_name.text = names[0]
                login_status.text = "Signed In"
                showMenu = true

            } else {

                access.visibility = View.VISIBLE
            }

            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbarLayout)

            val appBarLayout = findViewById<AppBarLayout>(R.id.appBarLayout)

            appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
                var isShow = true
                var scrollRange = -1

                override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.totalScrollRange
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbarLayout.title = "Nairobi City County ePayments"
                        isShow = true
                    } else if (isShow) {
                        collapsingToolbarLayout.title = " "//careful there should a space between double quote otherwise it wont work
                        isShow = false
                    }
                }
            })

            parking_view.setOnClickListener {
                val dailyParking = DailyParkingFragment()

                dailyParking.show(supportFragmentManager, dailyParking.tag)
                dailyParking.isCancelable = false
            }

            houseRent_card.setOnClickListener {
                if (Api.getValue(this@naypayDasboard, "LoginStatus") == "login") {
                    val houseRent = HouseRentFragment()
                    houseRent.show(supportFragmentManager, houseRent.tag)
                    houseRent.isCancelable = false
                } else {
                    var loginFragment = LoginFragment()
                    LoginFragment.isLogin = true
                    loginFragment.show(fragmentManager, loginFragment.tag)
                    Toast.makeText(this@naypayDasboard, "Please Login to access", Toast.LENGTH_SHORT).show()
                }
            }

            materialRippleLayout.setOnClickListener {
                if (Api.getValue(this@naypayDasboard, "LoginStatus") == "login") {
                    val landRate = LandRateFragment()
                    landRate.show(supportFragmentManager, landRate.tag)
                    landRate.isCancelable = false
                } else {
                    var loginFragment = LoginFragment()
                    LoginFragment.isLogin = true
                    loginFragment.show(fragmentManager, loginFragment.tag)
                    Toast.makeText(this@naypayDasboard, "Please Login to access", Toast.LENGTH_SHORT).show()
                }
            }

            ubp_card.setOnClickListener {

                showCustomDialog()

            }

            status_card.setOnClickListener {
                val penalty = ParkingStatus()
                penalty.show(supportFragmentManager, penalty.tag)
                penalty.isCancelable = false
            }

            bill_card.setOnClickListener {
                if (Api.getValue(this@naypayDasboard, "LoginStatus") == "login") {
                    val miscellaneous = MiscellaneousFragment()
                    miscellaneous.show(supportFragmentManager, miscellaneous.tag)
                    miscellaneous.isCancelable = false
                } else {
                    var loginFragment = LoginFragment()
                    LoginFragment.isLogin = true
                    loginFragment.show(fragmentManager, loginFragment.tag)
                    Toast.makeText(this@naypayDasboard, "Please Login to access", Toast.LENGTH_SHORT).show()
                }
            }

            penalties_card.setOnClickListener {
                val penalties = ParkingPenalties()
                penalties.show(supportFragmentManager, penalties.tag)
                penalties.isCancelable = false
            }

            ads_card.setOnClickListener {
                AlertDialog.Builder(this@naypayDasboard)
                        .setTitle("Advertisement")
                        .setMessage("Coming Soon")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.


                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.panish)
                        .show()
            }
            stalls_card.setOnClickListener {
                AlertDialog.Builder(this@naypayDasboard)
                        .setTitle("Market Stalls")
                        .setMessage("Coming Soon")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.


                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.stall)
                        .show()
            }

            val appUpdater = AppUpdater(this)
            appUpdater.start()
        } catch (ex: Exception) {
            Log.e("", ex.localizedMessage)
        }

    }

    override fun onItemClick(view: View, position: Int) {
        current = position
        when (position) {
            0 -> {

                val dailyParking = DailyParkingFragment()

                dailyParking.show(supportFragmentManager, dailyParking.tag)
                dailyParking.isCancelable = false
            }
            1 -> {



            }
            2 -> {


            }
            3 -> {


            }
            4 -> {


            }
            5 -> {


            }
            6 -> {

            }
        }


    }

    override fun onSubItemClick(view: View, position: Int, isMain: Boolean?) {
        when (position) {
            /* 0 -> {
                 if ((!isMain!!) && behavior.state == BottomSheetBehavior.STATE_COLLAPSED || behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                     *//* behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    loadFragment(new DailyParkingFragment());
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);*//*

                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                }
            }
            1 -> {

                *//*if(!isMain&&behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){

                 *//**//* loadFragment(new MiscellaneousFragment());
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);*//**//*



                }
                else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }*//*

            }
            2 -> {
                val penalty = ParkingStatus()
                penalty.show(supportFragmentManager, penalty.tag)
                *//* if(!isMain&&behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){

                 *//**//* loadFragment(new ParkingStatus());
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);*//**//*

                }
                else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }*//*
            }
            3 -> {
                Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
            }*/
        }

    }

    private fun InflateViews(transActions: List<Payment>) {

        /*RecyclerView rvContacts = (RecyclerView) findViewById(R.id.transactions);

        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(transActions);
        adapter.setClickListener(naypayDasboard.this);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setItemAnimator(new DefaultItemAnimator());
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(naypayDasboard.this));*/
    }

    private fun showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        var viewGroup = findViewById<ViewGroup>(android.R.id.content)

        //then we will inflate the custom alert dialog xml that we created
        var dialogView = LayoutInflater.from(this).inflate(R.layout.ubp_link, viewGroup, false)
        dialogView.link.movementMethod = LinkMovementMethod.getInstance()
        //Now we need an AlertDialog.Builder object
        var builder = AlertDialog.Builder(this)

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView)

        //finally creating the alert dialog and displaying it
        var alertDialog = builder.create()
        alertDialog.show()
    }

    private fun LoadTransactions(response: String) {

        val transactions = Api.mGson.fromJson(response, PaymentsResponse::class.java)
        if (transactions.statusCode == 200) {

            InflateViews(transactions.payments)

        }
    }

    /*override fun onReciptItemClick(view: View, position: Int, uniqueNumber: String) {
        GetRecipt(uniqueNumber)
        Toast.makeText(this, uniqueNumber, Toast.LENGTH_SHORT).show()
    }*/

    private fun GetRecipt(unqCode: String) {
        Log.d("###", " Checking Payments")
        Api.getVolley(this, Api.GET, Api.GetTransactionReceipt + unqCode, "", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                val status = Api.mGson.fromJson(result, TransactionStatusResponse::class.java)
                if (status.statusCode == 200) {
                    Const.getInstance().transactionStatusResponse = status

                    startActivity(Intent(this@naypayDasboard, Recipts::class.java))

                }
            }
        })


    }


    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

    private fun dashBoardSLiderOffline() {

        /*SliderLayout mDemoSlider = (SliderLayout) findViewById(R.id.slider);*/
        /*HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("", "");*/
        /*url_maps.put("lhLAgbjJFNY", "https://nairobinews.nation.co.ke/wp-content/uploads/2018/11/Nairobi-City-II.jpg");
        url_maps.put("CuOsy0pmeDU", "https://www.kenyaengineer.co.ke/wp-content/uploads/2018/03/The-Pinnacle-Nairobi-2-min.jpg");
        url_maps.put("0wneNY4QNLo", "https://nairobinews.nation.co.ke/wp-content/uploads/2018/11/Nairobi-City-II.jpg");
        url_maps.put("0wneNY4QNLo2", "https://nairobinews.nation.co.ke/wp-content/uploads/2016/01/editor2325226663184264068.jpg");*/


        /*for (final String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            if (name.equalsIgnoreCase("NOVIDEO")) {
                                Toast.makeText(naypayDasboard.this, "No Video or Audio for this Banner", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = null;
                                if (naypayDasboard.this != null) {
                                    intent = YouTubeStandalonePlayer.createVideoIntent(naypayDasboard.this, "AIzaSyCkn56J552GBuinU1gYQ77HW05TbC5TrLI", name, 1, true, true);

                                }
                                startActivity(intent);
                            }
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(10000);
        mDemoSlider.addOnPageChangeListener(this);*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        var logoutItem = menu.findItem(R.id.item3)
        invalidateOptionsMenu()
        logoutItem.isVisible = showMenu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.item1 -> {
                /*if (Api.getValue(naypayDasboard.this, "LoginStatus").equals("login")) {
                    Intent i = new Intent(naypayDasboard.this, History.class);
                    startActivity(i);
                } else {
                    loginFragment = new LoginFragment();
                    loginFragment.isLogin = true;
                    loginFragment.show(getFragmentManager(), loginFragment.getTag());
                    Toast.makeText(this, "Please Login to access", Toast.LENGTH_SHORT).show();
                }*/
                AlertDialog.Builder(this@naypayDasboard)
                        .setTitle("History")
                        .setMessage("Coming Soon")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.


                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.history)
                        .show()

                return true
            }


            R.id.item3 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                } else {
                    // Swap without transition
                }
                Api.clearValue(this, "LoginStatus")
                Api.clearValue(this, "token")
                Api.clearValue(this, "FullNames")
                finish()
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }

    override fun onUserInteraction() {

    }

    private inner class MyTimerClass : Thread()

    fun screenShot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width,
                view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    companion object {

        private val EXTRA_IMAGE = "com.antonioleiva.materializeyourapp.extraImage"

        private val EXTRA_TITLE = "com.antonioleiva.materializeyourapp.extraTitle"

        fun navigate(activity: AppCompatActivity, transitionImage: View, viewModel: ViewModel) {

            /* Intent intent = new Intent(activity, DetailActivity.class);

        intent.putExtra(EXTRA_IMAGE, viewModel.getImage());

        intent.putExtra(EXTRA_TITLE, viewModel.getText());



        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);

        ActivityCompat.startActivity(activity, intent, options.toBundle());*/

        }
    }


}