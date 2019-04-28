package com.example.myhoroscope.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myhoroscope.Adapters.Adapter;
import com.example.myhoroscope.Helpers.DatabaseHelper;
import com.example.myhoroscope.R;
import com.example.myhoroscope.Models.ZodiacSign;

import java.util.ArrayList;
import java.util.List;

public class ZodiacActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<String> text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac);

        text = new ArrayList<>();

        recyclerView = findViewById(R.id.zodiacRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, text));

        DatabaseHelper db = new DatabaseHelper(this);

        String ariesDescription = "As the first sign in the zodiac, the presence of Aries always marks the beginning of something energetic and turbulent. " +
                "They are continuously looking for dynamic, speed and competition, always being the first in everything - from work to social gatherings.";

        String taurusDescription = "Practical and well-grounded, Taurus is the sign that harvests the fruits of labor. They feel the need to always be " +
                "surrounded by love and beauty, turned to the material world, hedonism, and physical pleasures.";

        String geminiDescription = "Expressive and quick-witted, Gemini represents two different personalities in one and you will never be sure which one you will face." +
                " They are sociable, communicative and ready for fun, with a tendency to suddenly get serious, thoughtful and restless.";

        String cancerDescription = "Deeply intuitive and sentimental, Cancer can be one of the most challenging zodiac signs to get to know. They are very emotional and sensitive, " +
                "and care deeply about matters of the family and their home. Cancer is sympathetic and attached to people they keep close.";

        String leoDescription = "People born under the sign of Leo are natural born leaders. They are dramatic, creative, self-confident, dominant and extremely difficult to resist," +
                " able to achieve anything they want to in any area of life they commit to. There is a specific strength to a Leo and their \"king of the jungle\" status.";

        String virgoDescription = "Virgos are always paying attention to the smallest details and their deep sense of humanity makes them one of the most careful signs of the zodiac. " +
                "Their methodical approach to life ensures that nothing is left to chance, and although they are often tender, their heart might be closed for the outer world.";

        String libraDescription = "People born under the sign of Libra are peaceful, fair, and they hate being alone. Partnership is very important for them, as their mirror and someone" +
                " giving them the ability to be the mirror themselves. These individuals are fascinated by balance and symmetry, they are in a constant chase for justice and equality.";

        String scorpioDescription = "Scorpio-born are passionate and assertive people. They are determined and decisive, and will research until they find out the truth. Scorpio is a great leader," +
                " always aware of the situation and also features prominently in resourcefulness. Scorpio is a Water sign and lives to experience and express emotions.";

        String sagittariusDescription = "Curious and energetic, Sagittarius is one of the biggest travelers among all zodiac signs. Their open mind and philosophical view motivates them " +
                "to wander around the world in search of the meaning of life. Sagittarius is extrovert, optimistic and enthusiastic, and likes changes.";

        String capricornDescription = "Capricorn is a sign that represents time and responsibility, and its representatives are traditional and often very serious by nature. " +
                "These individuals possess an inner state of independence that enables significant progress both in their personal and professional lives. They are masters of self-control" +
                " and have the ability to lead the way, make solid and realistic plans, and manage many people who work for them at any time.";

        String aquariusDescription = "Aquarius-born are shy and quiet , but on the other hand they can be eccentric and energetic. However, in both cases, they are deep thinkers and" +
                " highly intellectual people who love helping others. They are able to see without prejudice, on both sides, which makes them people who can easily solve problems.";

        String piscesDescription = "Pisces are very friendly, so they often find themselves in a company of very different people. Pisces are selfless, they are always willing to help others," +
                " without hoping to get anything back. Pisces is a Water sign and as such this zodiac sign is characterized by empathy and expressed emotional capacity.";

        db.addZodiacSign(new ZodiacSign("Aries", ariesDescription));
        db.addZodiacSign(new ZodiacSign("Taurus", taurusDescription));
        db.addZodiacSign(new ZodiacSign("Gemini", geminiDescription));
        db.addZodiacSign(new ZodiacSign("Cancer", cancerDescription));
        db.addZodiacSign(new ZodiacSign("Leo", leoDescription));
        db.addZodiacSign(new ZodiacSign("Virgo", virgoDescription));
        db.addZodiacSign(new ZodiacSign("Libra", libraDescription));
        db.addZodiacSign(new ZodiacSign("Scorpio", scorpioDescription));
        db.addZodiacSign(new ZodiacSign("Sagittarius", sagittariusDescription));
        db.addZodiacSign(new ZodiacSign("Capricorn", capricornDescription));
        db.addZodiacSign(new ZodiacSign("Aquarius", aquariusDescription));
        db.addZodiacSign(new ZodiacSign("Pisces", piscesDescription));

        List<ZodiacSign> zodiacSigns = db.getAllZodiacSigns();

        for(ZodiacSign zs : zodiacSigns) {
            String log = "Sign: " + zs.getSign() + "\n" + "Description: " + zs.getDescription() + "\n";
            text.add(log);
        }

    }


}
