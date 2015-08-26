package com.gmail.s8521444;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class SimpleFragment extends Fragment implements ScreenShotable {

    private static final String ARG_NUMBER = "number";
    private int number;

    private View containerView;
    private Bitmap bitmap;

    public static SimpleFragment newInstance(final int number) {
        final SimpleFragment fragment = new SimpleFragment();

        final Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);

        return fragment;
    }

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getInt(ARG_NUMBER, 0);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        ((TextView) rootView.findViewById(R.id.number_text)).setText(String.valueOf(number));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void takeScreenShot() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                final Bitmap bitmap = Bitmap.createBitmap(
                        containerView.getWidth(),
                        containerView.getHeight(),
                        Bitmap.Config.ARGB_8888);
                final Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                SimpleFragment.this.bitmap = bitmap;
            }
        };

        thread.start();
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}
