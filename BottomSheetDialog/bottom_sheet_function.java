  protected void showShareAppBottomSheet() {

        final View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_item_layout, null);

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.show();

        /*LinearLayout btnShareApp = view.findViewById(R.id.btn_share_app);
        btnShareApp.setOnClickListener(v -> {

            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
                finish();
            }


        });

        ImageView btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
        });*/


    }