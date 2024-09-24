package com.example.lab2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.adapter.CharacterAdapter;
import com.example.lab2.entity.Character;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private List<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.charactersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        characterList = new ArrayList<>();
        characterList.add(new Character("Ariel", "Driven by her independent spirit and curiosity (to both Sebastian and her father’s chagrin!), Ariel is always looking to learn about the treasures she finds and the people around her.\n" +
                "\n" + "Through her courageous journey of self-discovery, she finds her place in the world and creates a new connection between her two kingdoms of land and sea. Whether on feet or on fins, Ariel’s resilience and determination shine through as she stands up for what she wants, and she always finds the courage to dive into something new.",
                R.drawable.ariel));
        characterList.add(new Character("Aurora", "Also known as Briar Rose, Aurora is graceful, playful, and optimistic. She knows that a wonderful future awaits, if you just have the courage to dream it, and her joy for life is reflected in her imaginative interactions with her forest friends.\n" +
                "\n" + "With her positive demeanor, Aurora quickly creates deep, loyal connections — with her animal friends, the good fairies, and even a passing prince! Aurora believes in a wish and remains hopeful that she will find the adventure she is looking for.",
                R.drawable.aurora));
        characterList.add(new Character("Belle", "Belle is imaginative and smart, and comfortable being authentically herself. She adores a good book, because different stories bring her to new places, introduce her to new people, and allow her to see new perspectives. While she yearned for adventure beyond her provincial town, she had no idea that her selfless act of defending her father would be the beginning of a tale beyond even her wildest dreams.\n" +
                "\n" + "In the Beast’s enchanted castle, where plates dance and teapots sing, Belle learns that she shouldn’t judge a book—or a Beast—by its cover. With compassion and courage, Belle writes the ending to her own story, where even the scariest beast can learn to love, and a girl from a little town finds not only the adventure she was looking for, but also love, family, and new friends.",
                R.drawable.belle));
        characterList.add(new Character("Cinderella", "Cinderella knows that being a positive influence is a sort of magic that can transform the world, so she is warm and sincere with everyone she meets. Whether it’s saving the smallest mouse from mean ole Lucifer, enduring her step-sisters’ music practice, or meeting the Prince himself, she treats everyone with respect, compassion, and remembers to keep her sense of humor.\n" +
                "\n" + "Even when tasked with an impossible to-do list, Cinderella believes in her own abilities and perseveres, hopeful and determined to overcome any challenge she faces. Despite setbacks, Cinderella creates the circumstances to find her own place in the world.",
                R.drawable.cinderella));
        characterList.add(new Character("Jasmine", "Jasmine is incredibly independent and strong. She isn’t afraid to speak her mind, no matter who she’s up against, and won’t hesitate to stand up for what’s right. The Princess of Agrabah, Jasmine is extremely compassionate and caring towards the people in her kingdom, her family, and her friends — especially her most loyal friend, her tiger Rajah.\n" +
                "\n" + "She knows that she shouldn’t let anyone else decide her future, and in her daring journey beyond the palace walls, Jasmine breaks out of her comfort zone, creating a whole new world and a new era for Agrabah.",
                R.drawable.jasmine));
        characterList.add(new Character("Moana", "Moana is a strong-willed, independent wayfinder. Though she has moments of self-doubt, she has great pride in who she is and where she comes from, and doesn’t back away from new challenges.\n" +
                "\n" + "Moana approaches new experiences and tasks with the utmost determination and will stand her ground to fight for what she values, even when all seems lost. Moana creates her own path, and knows that when you push yourself to new limits, you can cross any ocean.",
                R.drawable.moana));
        characterList.add(new Character("Mulan", "Mulan is clever and down-to-earth, and with the help of her new friends, proves her worth to her family and country by becoming a legendary warrior and savior of China. Mulan shows courage, passion, and determination in all that she does — defying cultural convention and proving that anything is possible when you believe in yourself.\n" +
                "\n" + "Mulan creates meaningful connections with unexpected friends and rallies the troops around her creative ideas, proving to all that a woman can achieve just as much as any man can.",
                R.drawable.mulan));
        characterList.add(new Character("Rapunzel", "Rapunzel is the creative, spirited Princess of Corona, with a strong sense of curiosity about the world, and a willingness to step outside of her comfort zone. Despite not knowing the world beyond her tower, she finds the courage to push herself forward and discover new friends, find her true family and pursue her dreams.\n" +
                "\n" + "Rapunzel’s enthusiasm is contagious, and her vibrant and optimistic personality charms everyone around her.",
                R.drawable.rapunzel));
        characterList.add(new Character("Snow White", "Snow White knows that every day is an opportunity to make new friends, and that it’s best to be kind to everyone, even if they’re grumpy!\n" +
                "\n" + "She sees the world in a joyful, unguarded way and expresses a purity and honesty in her presence and demeanor. When sharing her optimistic point of view, she truly believes that she can fill the world with joy. Always turning a negative into a positive, she loves to express her optimistic ideals in a song – especially when tackling a daunting project or sharing her feelings. With charm and whimsy, Snow White creates a community out of the forest animals and the friends she finds on her journey.",
                R.drawable.snow_white));
        characterList.add(new Character("Tiana", "From a young age, Tiana learned that a dream is a lot of hard work and a little bit of magic. A visionary who dreamt—with her father—of sharing the joys of good food with others, Tiana is passionately focused on achieving her goal of opening her restaurant.\n" +
                "\n" + "Through her unexpected journey into the bayou with Naveen, Tiana discovers that dreams can grow and change. Despite many obstacles, she always finds the courage to keep on trying, ultimately creating a community around her even deeper than she could have originally imagined.",
                R.drawable.tiana));

        adapter = new CharacterAdapter(characterList, this);
        recyclerView.setAdapter(adapter);
    }
}