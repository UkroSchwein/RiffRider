package com.example.riffrider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    private MyViewModel itemViewModel;
    private RecyclerView recyclerView;
    private SongAdapter adapter;
    private List<Song> songList;

    private ArrayList<Song> genreList;

    List<String> selectedGenres;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_layout, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SongAdapter(songList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                NavController navController = Navigation.findNavController(requireView());

                Bundle args = new Bundle();
                args.putString("songText", song.getLyrics());
                args.putString("songName", song.getTitle());
                args.putString("songAuthor", song.getArtist());
                args.putString("songGenre",song.getGenre());
                args.putInt("songId", song.songId);
                navController.navigate(R.id.songFragment, args);
            }
        });



        itemViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        itemViewModel.CreateDB(getContext());
        itemViewModel.getItemList().observe(getViewLifecycleOwner(), itemList -> {
            adapter.setItems(itemList);
        });

//        itemViewModel.addSong("Smells like teen spirit", "Nirvana", "Вступление: Em  A  G5  C5 }x4\n" +
//                "\n" +
//                "Куплет:\n" +
//                "Em    A     G5\n" +
//                "Load up on guns\n" +
//                "C5          Em\n" +
//                "Bring your friends\n" +
//                "     A      G5\n" +
//                "It's fun to lose\n" +
//                "    C5     Em\n" +
//                "And to pretend\n" +
//                "      A      G5\n" +
//                "She's overboard\n" +
//                "C5         Em\n" +
//                "Myself assured\n" +
//                "  A      G5      C5     Em\n" +
//                "I know I know a dirty word\n" +
//                "Em      A      G5      C5\n" +
//                "Hello, hello, hello, how low (x4)\n" +
//                "\n" +
//                "Припев:\n" +
//                "Em\n" +
//                "With the lights out\n" +
//                "A         G5\n" +
//                "It's less dangerous\n" +
//                "C5        Em\n" +
//                "Here we are now\n" +
//                "A       G5\n" +
//                "Entertain us\n" +
//                "C5      Em\n" +
//                "I feel stupid\n" +
//                "A       G5\n" +
//                "And contagious\n" +
//                "C5        Em\n" +
//                "Here we are now\n" +
//                "A       G5\n" +
//                "Entertain us\n" +
//                "C5    Em\n" +
//                "А mulato\n" +
//                "A     G5\n" +
//                "An albino\n" +
//                "C5     Em\n" +
//                "А misquito\n" +
//                "A     G5\n" +
//                "My libido\n" +
//                "C5\n" +
//                "Yeah\n" +
//                "\n" +
//                "Куплет:\n" +
//                "Em    A     G5\n" +
//                "I'm worse at \n" +
//                "C5          Em\n" +
//                "what I do best\n" +
//                "     A      G5\n" +
//                "And for this gift \n" +
//                "C5          Em\n" +
//                "I feel blessed\n" +
//                "     A      G5\n" +
//                "Our little group \n" +
//                "C5         Em\n" +
//                "has always been\n" +
//                "  A      G5      C5     Em\n" +
//                "And always will until the end\n" +
//                "Em      A      G5     C5\n" +
//                "Hello, hello, hello, how low (x4)\n" +
//                "\n" +
//                "Припев:\n" +
//                "Em\n" +
//                "With the lights out\n" +
//                "A         G5\n" +
//                "It's less dangerous\n" +
//                "C5        Em\n" +
//                "Here we are now\n" +
//                "A       G5\n" +
//                "Entertain us\n" +
//                "C5      Em\n" +
//                "I feel stupid\n" +
//                "A       G5\n" +
//                "And contagious\n" +
//                "C5        Em\n" +
//                "Here we are now\n" +
//                "A       G5\n" +
//                "Entertain us\n" +
//                "C5    Em\n" +
//                "А mulato\n" +
//                "A     G5\n" +
//                "An albino\n" +
//                "C5     Em\n" +
//                "А misquito\n" +
//                "A     G5\n" +
//                "My libido\n" +
//                "C5\n" +
//                "Yeah\n" +
//                "\n" +
//                "Куплет:\n" +
//                "Em    A    G5\n" +
//                "And I forget \n" +
//                "C5          Em\n" +
//                "just why I taste\n" +
//                "     A      G5\n" +
//                "Oh yeah, I guess \n" +
//                "C5          Em\n" +
//                "it makes me smile\n" +
//                "     A      G5\n" +
//                "I found it hard, \n" +
//                "C5          Em\n" +
//                "it's hard to find\n" +
//                "  A      G5      C5     Em\n" +
//                "Oh well, whatever, nevermind\n" +
//                "Em      A      G5      C5\n" +
//                "Hello, hello, hello, how low (x4)\n" +
//                "\n" +
//                "Припев:\n" +
//                "Em\n" +
//                "With the lights out\n" +
//                "A         G5\n" +
//                "It's less dangerous\n" +
//                "C5        Em\n" +
//                "Here we are now\n" +
//                "A       G5\n" +
//                "Entertain us\n" +
//                "C5      Em\n" +
//                "I feel stupid\n" +
//                "A       G5\n" +
//                "And contagious\n" +
//                "C5        Em\n" +
//                "Here we are now\n" +
//                "A       G5\n" +
//                "Entertain us\n" +
//                "C5    Em\n" +
//                "А mulato\n" +
//                "A     G5\n" +
//                "An albino\n" +
//                "C5     Em\n" +
//                "А misquito\n" +
//                "A     G5\n" +
//                "My libido\n" +
//                "C5\n" +
//                "Yeah", "Альтернатива");
//        itemViewModel.addSong("Come as you are", "Nirvana",
//                "Em           D            Em\n" +
//                "Come as you are, as you were\n" +
//                "      D           Em\n" +
//                "As I want you to be\n" +
//                "Em           D         Em\n" +
//                "As a friend, as a friend\n" +
//                "      D       Em\n" +
//                "As an old enemy\n" +
//                "Em           D         Em\n" +
//                "Take your time, hurry up\n" +
//                "      D       Em\n" +
//                "Choice is yours don't be late\n" +
//                "Em           D         Em\n" +
//                "Take a rest, as a friend\n" +
//                "      D       Em\n" +
//                "As an old enemyyyy yeah\n" +
//                "Em    G\n" +
//                "Memoria x3 \n" +
//                "\n" +
//                "Em           D         Em\n" +
//                "Come dowsed in mud, soaked in bleach\n" +
//                "      D       Em\n" +
//                "As I want you to be\n" +
//                "Em           D         Em\n" +
//                "As a trend, as a friend\n" +
//                "      D       Em\n" +
//                "As an old enemyyyy yeah\n" +
//                "Em    G\n" +
//                "Memoria\n" +
//                "\n" +
//                "Em           D         Em\n" +
//                "Well I swear that I don't have a gun\n" +
//                "      D       Em\n" +
//                "No I don't have a gun\n" +
//                "      D       Em\n" +
//                "No I don't have a gun\n" +
//                "Em    G\n" +
//                "Memoria\n" +
//                "\n" +
//                "A        C           \n" +
//                "Well I swear that \n" +
//                "      D       Em\n" +
//                "I don't have a gun x3 ", "Альтернатива");
//        itemViewModel.addSong("Heart Shaped Box", "Nirvana", "Intrо: Riff А (G# - E) - Riff В (C#) }x\n" +
//                "\n" +
//                "Verse 1:\n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "She eyes me like a pisces when I am weak \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "I´ve been lосkеd inside уоur Heart-Shaped bох fоr a week \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "I was drawn intо уоur magnet tar pit trap \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "I wish I соuld eat уоur cancer when уоu turn back \n" +
//                "\n" +
//                "CHOURUS: x2\n" +
//                "G#   E     C# (Riff В)              \n" +
//                "Hey, wait, I´ve gоt a new соnрlаint \n" +
//                "G# E       C#                         \n" +
//                "Fоrеvеr in debt tо уоur priceless advice \n" +
//                "E  C#  pause E C# (Riff С)      \n" +
//                "Gоin´ by\n" +
//                "\n" +
//                "Verse 2:\n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "Meat-eating оrсhids fоrgivе nо оnе just yet \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "Cut myself angel´s hair and baby´s breath \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "Brоkеn hymen оf уоur highness I´m left black \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "Thrоw dоwn уоur umbilical nооsе sо I can climb right back\n" +
//                "\n" +
//                "CHOURUS: x2\n" +
//                "G#   E     C# (Riff В)              \n" +
//                "Hey, wait, I´ve gоt a new соnрlаint\n" +
//                "G# E       C#                         \n" +
//                "Fоrеvеr in debt tо уоur priceless advice \n" +
//                "E  C#  pause E C# (Riff С)      \n" +
//                "Gоin´ by\n" +
//                "\n" +
//                "Sоlо:                                           x2  let ring\n" +
//                "E|------------------------------------------------|------|\n" +
//                "B|о-------9~~--------7~~--------5--4-2-0---------о|------|\n" +
//                "G|----------------------------3-------------------|------|\n" +
//                "D|-----6---------4----------2---------------------|-6----|\n" +
//                "A|о----------------------------------------------о|-6----|\n" +
//                "D|------------------------------------------------|-4----|\n" +
//                "\n" +
//                "Verse 1:\n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "She eyes me like a pisces when I am weak \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "I´ve been lосkеd inside уоur Heart-Shaped bох fоr a week \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "I was drawn intо уоur magnet tar pit trap \n" +
//                "Riff А  - Riff В -      Riff А  - Riff С\n" +
//                "I wish I соuld eat уоur cancer when уоu turn back \n" +
//                "\n" +
//                "CHOURUS: x2\n" +
//                "G#   E     C# (Riff В)              \n" +
//                "Hey, wait, I´ve gоt a new соnрlаint\n" +
//                "G# E       C#                         \n" +
//                "Fоrеvеr in debt tо уоur priceless advice \n" +
//                "E  C#  pause E C# (Riff С)      \n" +
//                "Gоin´ by", "Альтернатива");
//        itemViewModel.addSong("Teenagers", "My Chemical Romance", "D                          D\n" +
//                "they're gonna clean up your looks\n" +
//                "D                          D\n" +
//                "with all the lies and the books\n" +
//                "D                        A\n" +
//                "to make a citizen out of you\n" +
//                "              G           G\n" +
//                "because they sleep with a gun\n" +
//                "             D\n" +
//                "and keep an eye on you son\n" +
//                "            A                      D\n" +
//                "so they can watch all the things you do\n" +
//                "\n" +
//                "D                          D\n" +
//                "because the drugs never work\n" +
//                "     D               D\n" +
//                "they gonna give you a smirk\n" +
//                "D              D\n" +
//                "cause they got methods\n" +
//                "                 A\n" +
//                "of keeping you clean\n" +
//                "               G\n" +
//                "they're gonna rip out your heads\n" +
//                "          D\n" +
//                "your aspirations to shreds\n" +
//                "         A                      D\n" +
//                "another cog in the murder machine\n" +
//                "\n" +
//                "Chorus:\n" +
//                "             D                          D\n" +
//                "they say all teenagers scare the living shit out of me\n" +
//                "           D                    A\n" +
//                "they could care less as long as someone will bleed\n" +
//                "   G\n" +
//                "so darken your clothes\n" +
//                "           D\n" +
//                "or strike a violent pose\n" +
//                "               A\n" +
//                "maybe they'll leave you alone\n" +
//                "        D\n" +
//                "but not me\n" +
//                "\n" +
//                "D                          D\n" +
//                "the boys and girls in the clique\n" +
//                "      D                 D\n" +
//                "the awful names that they stick \n" +
//                "           D                  A\n" +
//                "you're never gonna fit in much kid\n" +
//                "              G\n" +
//                "but if you're troubled and hurt\n" +
//                "             D\n" +
//                "what you got under your shirt\n" +
//                "               A                            D\n" +
//                "we'll make them pay for the things that they did\n" +
//                "\n" +
//                "Chorus:\n" +
//                "             D                          D\n" +
//                "they say all teenagers scare the living shit out of me\n" +
//                "           D                    A\n" +
//                "they could care less as long as someone will bleed\n" +
//                "   G\n" +
//                "so darken your clothes\n" +
//                "           D\n" +
//                "or strike a violent pose\n" +
//                "               A\n" +
//                "maybe they'll leave you alone\n" +
//                "        D\n" +
//                "but not me\n" +
//                "* oh yeah *\n" +
//                "\n" +
//                "Chorus:\n" +
//                "             D                          D\n" +
//                "they say all teenagers scare the living shit out of me\n" +
//                "           D                    A\n" +
//                "they could care less as long as someone will bleed\n" +
//                "   G\n" +
//                "so darken your clothes\n" +
//                "           D\n" +
//                "or strike a violent pose\n" +
//                "               A\n" +
//                "maybe they'll leave you alone\n" +
//                "        D\n" +
//                "but not me\n" +
//                "D\n" +
//                "All together now,\n" +
//                "             D                          D\n" +
//                "teenagers scare the living shit out of me\n" +
//                "           D                    A\n" +
//                "they could care less as long as someone will bleed\n" +
//                "G\n" +
//                "so darken your clothes\n" +
//                "           D\n" +
//                "or strike a violent pose\n" +
//                "               A\n" +
//                "maybe they'll leave you alone\n" +
//                "        D\n" +
//                "but not me\n" +
//                "             D                          D\n" +
//                "teenagers scare the living shit out of me\n" +
//                "           D                    A\n" +
//                "they could care less as long as someone will bleed\n" +
//                "G\n" +
//                "so darken your clothes\n" +
//                "           D\n" +
//                "or strike a violent pose\n" +
//                "               A\n" +
//                "maybe they'll leave you alone\n" +
//                "        D\n" +
//                "but not me", "Альтернатива");
//        itemViewModel.addSong("Na Na Na", "My Chemical Romance", " Intro:\n" +
//                "G# D# C# F# E D#\n" +
//                "e|-----------------|-----------------|-----------------|-----------------|\n" +
//                "B|-----------------|-----------------|-----------------|-----------------|\n" +
//                "G|-----------------|-----------4-----|-----------------|-----------------|\n" +
//                "D|-6---6-4-6---6-4-|-6---6-4-6---6-4-|-----------------|-----------4-----|\n" +
//                "A|-----------------|-----------------|-6---6-4-6---6-4-|-6---6-4-6---6-5-|\n" +
//                "E|-----------------|-----------------|-----------------|-----------------|\n" +
//                "\n" +
//                "e|-----------------|-----------------|-----------------|-----------------|\n" +
//                "B|-----------------|-----------------|-----------------|-----------------|\n" +
//                "G|-----------------|-----------------|-----------------|-----------------|\n" +
//                "D|-----------------|-4---4---4---4-5-|-6---6-4-6---4---|---8-8-8-8-8-8-8-|\n" +
//                "A|-4---4---4---4---|-------6---------|-----------------|---6-6-6-6-6-6-6-|\n" +
//                "E|-------7-------7-|-----------------|-----------------|-----------------|\n" +
//                "Na na-na-na, na-na-na-na-na-na-na-na-na\n" +
//                "\n" +
//                "G#\n" +
//                "Drugs, gimme drugs, gimme drugs\n" +
//                "                            D#\n" +
//                "I don't need them but I'll sell what you got\n" +
//                "                         C#\n" +
//                "Take the cash and I'll keep it eight legs to the wall\n" +
//                "         F#\n" +
//                "Hit the gas, kill 'em all\n" +
//                "    G#                  D#\n" +
//                "And we crawl, and we crawl, and we crawl\n" +
//                "D#\n" +
//                "You be my detonator\n" +
//                "\n" +
//                "G#\n" +
//                "Love, gimme love, gimme love\n" +
//                "                          D#m\n" +
//                "I don't need it but I'll take what I want\n" +
//                "                          C#\n" +
//                "From your heart and I'll keep it in a bag\n" +
//                "                   F#\n" +
//                "In a box, put an X on the floor\n" +
//                "           G#\n" +
//                "Gimme more, gimme more, gimme more\n" +
//                "D#\n" +
//                "Shut up and sing it with me\n" +
//                "\n" +
//                "Chorus:\n" +
//                "B\n" +
//                "Na na-na-na, na-na-na-na-na-na-na-na-na\n" +
//                "                   F#\n" +
//                "From mall security to every enemy\n" +
//                "G#m                E       G\n" +
//                " We're on your property standing in V formation\n" +
//                "B                      F#\n" +
//                " let's blow an artery, eat plastic surgery\n" +
//                "G#m           E         G\n" +
//                " Keep your apology give us more detonation\n" +
//                "\n" +
//                "G#m\n" +
//                "(More! Gimme more! Gimme more!)\n" +
//                "\n" +
//                "                    G#\n" +
//                "Oh, let me tell ya 'bout the sad man\n" +
//                "                          D#\n" +
//                "Shut up and let me see your jazz hands\n" +
//                "\t           C#\n" +
//                "Remember when you were a madman\n" +
//                "\t\t    F#\n" +
//                "Thought you was Batman\n" +
//                "           G#\n" +
//                "Hit the party with a gas can\n" +
//                " D#\n" +
//                "Kiss me you animal\n" +
//                "\n" +
//                "B\n" +
//                "Na na-na-na, na-na-na-na-na-na-na-na-na\n" +
//                "           F#\n" +
//                "You run the company.\n" +
//                "\t    G#m\n" +
//                "F* like a Kennedy\n" +
//                "\t\t     E        G\n" +
//                "I think we'd rather be burning your information\n" +
//                "B\n" +
//                "Na na-na-na, na-na-na-na-na-na-na-na-na\n" +
//                "           F#\n" +
//                "Lets blow and artery\n" +
//                "          G#m\n" +
//                "Eat plastic surgery\n" +
//                "       \t\t E         G\n" +
//                "Keep your apology give us more detonation\n" +
//                "\n" +
//                "G#m           G\n" +
//                "And right here right now\n" +
//                "F#m         C#\n" +
//                "All the way in Battery City\n" +
//                "E           D                G#\n" +
//                "Little children, raise their open filthy palms\n" +
//                "G#\n" +
//                "Like tiny daggers up to heaven\n" +
//                "G#m      G           F#\n" +
//                "And all the juvee halls and Ritalin rats\n" +
//                "    Fm                      E\n" +
//                "Ask angels made from neon and fcking garbage\n" +
//                "G        G#m\n" +
//                "Scream out \"What will save us?\"\n" +
//                "G#m\n" +
//                "And the sky opened up\n" +
//                "\n" +
//                "                       D#\n" +
//                "Everybody wants to change the world\n" +
//                "Bm                    C#\n" +
//                "Everybody wants to change the world\n" +
//                "   E        D#m         \n" +
//                "But no one, no one wants to die\n" +
//                "G#\n" +
//                "Wanna try, wanna try, wanna try\n" +
//                "   G#m\n" +
//                "Wanna try, wanna try, oh\n" +
//                "G#\n" +
//                "I'll be your detonator\n" +
//                "\n" +
//                "B F# G#m E G\n" +
//                "\n" +
//                "B\n" +
//                "Na na-na-na, na-na-na-na-na-na-na-na-na\n" +
//                "\t       F#\n" +
//                "Make no apologies\n" +
//                "            G#m\n" +
//                "It's death or victory\n" +
//                "G#\n" +
//                "On my authority \n" +
//                "E\n" +
//                "Crash and burn\n" +
//                "G\n" +
//                "Young and loaded\n" +
//                "B\n" +
//                "Na na-na-na, na-na-na-na-na-na-na-na-na\n" +
//                "\t\t     F#\n" +
//                "Drop like a bullet shell\n" +
//                "\t\t      G#m\n" +
//                "Dress like a sleeper cell\n" +
//                "                E            G\n" +
//                "I'd rather Go to hell than be in purgatory\n" +
//                "E              G\n" +
//                "Cut my hair gag and bore me\n" +
//                "E         G           B\n" +
//                "Pull this pin let this world explode", "Альтернатива");
//        itemViewModel.addSong("Helena", "My Chemical Romance", "Intro:\n" +
//                "C#m                        E     \n" +
//                "Long ago, Just like the hearse, you \n" +
//                "B      G#m7     A     A/G#  A     C#m    B\n" +
//                "die to get in   again We    are so far from you\n" +
//                " \n" +
//                "Verse:\n" +
//                "C#m \n" +
//                "Burning on Just like a \n" +
//                "E           B       G#m7 A   A/G#\n" +
//                "match you strike to incinerate. The\n" +
//                "A           C#m      B\n" +
//                " lives of everyone you know\n" +
//                "\n" +
//                "Pre-Chorus:\n" +
//                "E                           A\n" +
//                "And what's the worst you take From every \n" +
//                "C#m       B\n" +
//                "Heart you break\n" +
//                "E                      A\n" +
//                "And like a blade you stain Well, I've been\n" +
//                "C#m       B\n" +
//                "holding on tonight\n" +
//                "\n" +
//                "Chorus 1:\n" +
//                "E                              B\n" +
//                "What's the worst that I could say? Things are\n" +
//                "Cdim    C#m                   Cdim\n" +
//                "better if I stay So long and goodnight\n" +
//                "    Ab\n" +
//                "So long and goodnight\n" +
//                "\n" +
//                "Verse:\n" +
//                "C#m\n" +
//                "Came a time When every \n" +
//                "E       B                G#m7   A\n" +
//                "star fall Brought you to tears  again\n" +
//                "A/G# A           C#m     B\n" +
//                "We   are the very hurt you sold\n" +
//                "\n" +
//                "Pre-Chorus:\n" +
//                "E                           A\n" +
//                "And what's the worst you take From every \n" +
//                "C#m       B\n" +
//                "Heart you break\n" +
//                "E                      A\n" +
//                "And like a blade you stain Well, I've been\n" +
//                "C#m       B\n" +
//                "holding on tonight\n" +
//                "\n" +
//                "Chorus 1:\n" +
//                "E                              B\n" +
//                "What's the worst that I could say? Things are\n" +
//                "Cdim    C#m                   Cdim\n" +
//                "better if I stay So long and goodnight\n" +
//                "    Ab\n" +
//                "So long and goodnight \n" +
//                "\n" +
//                "Chorus 2:\n" +
//                "E                          B\n" +
//                "Well, if you carry on this way. Things are \n" +
//                "Cdim     C#m\n" +
//                "better if I stay So long and goodnight\n" +
//                "   Cdim        Ab\n" +
//                "So long and goodnight\n" +
//                "\n" +
//                "Bridge:\n" +
//                "C#m A   E   B \n" +
//                "Can you hear me?\n" +
//                "C#m A   E   B \n" +
//                "Are you near me?\n" +
//                "C#m    A           E         B \n" +
//                "Can we pretend? To leave and then\n" +
//                "C#m       \n" +
//                "We'll meet again When\n" +
//                " E         B \n" +
//                " both our cars collide\n" +
//                "\n" +
//                "Chorus 1:\n" +
//                "E                              B\n" +
//                "What's the worst that I could say? Things are\n" +
//                "Cdim    C#m                   Cdim\n" +
//                "better if I stay So long and goodnight\n" +
//                "    Ab\n" +
//                "So long and goodnight \n" +
//                "\n" +
//                "Chorus 2:\n" +
//                "E                          B\n" +
//                "Well, if you carry on this way. Things are \n" +
//                "Cdim     C#m\n" +
//                "better if I stay So long and goodnight\n" +
//                "   Cdim        Ab\n" +
//                "So long and goodnight\n" +
//                "\n" +
//                "Outro: C#m E B G#m7 A", "Альтернатива");
//        itemViewModel.addSong("Boulevard Of Broker Dreams", "Green Day", "Fm        G#\n" +
//                " I walk a lonely road\n" +
//                "    D#             A#           Fm\n" +
//                "The only one that I have ever known\n" +
//                "           G#        \n" +
//                "Don't know where it goes \n" +
//                "D#                A#              Fm\n" +
//                "But it's home to me and I walk alone\n" +
//                "\n" +
//                "Fm G# D# A#\n" +
//                "\n" +
//                "Fm            G#   \n" +
//                "  I walk this empty street\n" +
//                "D#           A#              Fm      \n" +
//                "On the Boulevard of Broken Dreams\n" +
//                "          G#    \n" +
//                "Where the city sleeps\n" +
//                "    D#            A#               Fm\n" +
//                "And I'm the only one and I walk alone\n" +
//                "\n" +
//                "G#  \n" +
//                "\n" +
//                "  D#    A#\n" +
//                "I walk alone\n" +
//                "        Fm\n" +
//                "I walk alone\n" +
//                "\n" +
//                "G# \n" +
//                "\n" +
//                "  D#     A# \n" +
//                "I walk alone\n" +
//                "A#\n" +
//                "I walk a...\n" +
//                "\n" +
//                "Chorus:\n" +
//                "C#    G#            D#             A#\n" +
//                "  My shadow's the only one that walks beside me\n" +
//                "C#    G#       D#                A#\n" +
//                "  My shallow heart's the only thing that's beating\n" +
//                "C#     G#       D#                A#\n" +
//                "  Sometimes I wish someone out there will find me\n" +
//                "C#      G#      C\n" +
//                "  'Til then I walk alone\n" +
//                "\n" +
//                "Fm      G#      D#      A#     Fm\n" +
//                "  Ah-ah, Ah-ah, Ah-ah, Aaah-ah,\n" +
//                "      G#      D#    A#   \n" +
//                "Ah-ah, Ah-ah, Ah-ah\n" +
//                "\n" +
//                "Fm            G#        \n" +
//                "  I'm walking down the line\n" +
//                "D#               A#               Fm\n" +
//                "That divides me somewhere in my mind\n" +
//                "       G#  \n" +
//                "On the border line\n" +
//                "D#               A#             Fm  \n" +
//                "Of the edge and where I walk alone\n" +
//                "\n" +
//                "Fm G# D# A# \n" +
//                "\n" +
//                "Fm       G#            \n" +
//                "  Read between the lines\n" +
//                "D#                    A#            Fm\n" +
//                "What's fucked up and everything's alright\n" +
//                "         G#          \n" +
//                "Check my vital signs\n" +
//                "   D#              A#                 Fm\n" +
//                "To know I'm still alive and I walk alone\n" +
//                "\n" +
//                "G# \n" +
//                "\n" +
//                " D#     A#\n" +
//                "I walk alone\n" +
//                "        Fm\n" +
//                "I walk alone\n" +
//                " \n" +
//                "G#  \n" +
//                "\n" +
//                "  D#    A#\n" +
//                "I walk alone    \n" +
//                "A#\n" +
//                "I walk a...\n" +
//                "\n" +
//                "Chorus:\n" +
//                "C#    G#            D#             A#\n" +
//                "  My shadow's the only one that walks beside me\n" +
//                "C#    G#       D#                A#\n" +
//                "  My shallow heart's the only thing that's beating\n" +
//                "C#     G#       D#                A#\n" +
//                "  Sometimes I wish someone out there will find me\n" +
//                "C#      G#      C\n" +
//                "  'Til then I walk alone\n" +
//                "\n" +
//                "Fm      G#      D#      A#     Fm\n" +
//                "  Ah-ah, Ah-ah, Ah-ah, Aaah-ah\n" +
//                "      G#     D#\n" +
//                "Ah-ah, Ah-ah\n" +
//                "       A#\n" +
//                "I walk alone \n" +
//                "A#\n" +
//                "I walk a...\n" +
//                "\n" +
//                "Solo: C# G# D# Fm }x3\n" +
//                "      C# G# C\n" +
//                "\n" +
//                "Fm            G#           \n" +
//                "  I walk this empty street\n" +
//                "D#           A#              Fm\n" +
//                "On the Boulevard of Broken Dreams\n" +
//                "          G#      \n" +
//                "Where the city sleeps\n" +
//                "    D#            A#\n" +
//                "And I'm the only one and I walk a...\n" +
//                "\n" +
//                "C#    G#            D#             A#\n" +
//                "  My shadow's the only one that walks beside me\n" +
//                "C#    G#       D#                A#\n" +
//                "  My shallow heart's the only thing that's beating\n" +
//                "C#     G#       D#                A#\n" +
//                "  Sometimes I wish someone out there will find me\n" +
//                "C#      G#      C\n" +
//                "  'Til then I walk alone...", "Панк");
//        itemViewModel.addSong("21 Guns", "Green Day", "Dm      Bb       F              C\n" +
//                "Do you know what's worth fighting for\n" +
//                "Dm      Bb       F          C\n" +
//                "When it's not worth dying for?\n" +
//                "Dm      Bb       F        C\n" +
//                "Does it take you breath away\n" +
//                "Dm      Bb       F            C\n" +
//                "And you feel yourself suffocating?\n" +
//                "Dm      Bb       F              C\n" +
//                "Does the pain weight out the pride?\n" +
//                "Dm      Bb       F            C\n" +
//                "And you look for a place to hide?\n" +
//                "Dm      Bb       F              C\n" +
//                "Does someone break your heart inside?\n" +
//                "        Bb F C\n" +
//                "You're in ruins\n" +
//                "\n" +
//                "Припев:\n" +
//                "F    C/E Dm\n" +
//                "One, 21 guns\n" +
//                "C             Bb\n" +
//                "Lay down your arms\n" +
//                "F            C\n" +
//                "Give up the fight\n" +
//                "F   C/E Dm\n" +
//                "One, 21 guns\n" +
//                "C\n" +
//                "Throw up your \n" +
//                "Bb    F       C\n" +
//                "arms into the sky\n" +
//                "        Bb F C\n" +
//                "You and I\n" +
//                "\n" +
//                "Dm      Bb       F              C\n" +
//                "When you're at the end of the road\n" +
//                "Dm      Bb       F              C\n" +
//                "And you lost all sense of control\n" +
//                "Dm      Bb       F              C\n" +
//                "And your thoughts have taken their toll\n" +
//                "Dm      Bb       F              C\n" +
//                "When your mind breaks the spirit of your soul\n" +
//                "Dm      Bb       F           C\n" +
//                "Your faith walks on broken glass\n" +
//                "Dm      Bb       F         C\n" +
//                "And the hangover doesn't pass\n" +
//                "Dm      Bb       F       C\n" +
//                "Nothing's ever built to last\n" +
//                "         Bb  F C\n" +
//                "You're in ruins\n" +
//                "\n" +
//                "Припев:\n" +
//                "F    C/E Dm\n" +
//                "One, 21 guns\n" +
//                "C             Bb\n" +
//                "Lay down your arms\n" +
//                "F            C\n" +
//                "Give up the fight\n" +
//                "F   C/E Dm\n" +
//                "One, 21 guns\n" +
//                "C\n" +
//                "Throw up your \n" +
//                "Bb    F       C\n" +
//                "arms into the sky\n" +
//                "        Bb F C\n" +
//                "You and I\n" +
//                "\n" +
//                "Dm       Bb     F            C\n" +
//                "Did you try to live on your own\n" +
//                "Dm        Bb               F       A\n" +
//                "When you burned down the house and home?\n" +
//                "Dm        Bb        F           A\n" +
//                "Did you stand too close to the fire?\n" +
//                "         Bb                 C                F  \n" +
//                "Like a liar looking for forgiveness from a stone\n" +
//                "\n" +
//                "Guitar Solo: F C/E Dm C Bb F C }x2 Bb F A  \n" +
//                "             Dm Bb F C }x2\n" +
//                "\n" +
//                "Dm      Bb       F              C\n" +
//                "When it's time to live and let die\n" +
//                "Dm      Bb       F         C\n" +
//                "And you can't get another try\n" +
//                "Dm      Bb       F              C\n" +
//                "Something inside this heart has died\n" +
//                "       Bb  F C\n" +
//                "You're in ruins\n" +
//                "\n" +
//                "Припев:\n" +
//                "F    C/E Dm\n" +
//                "One, 21 guns\n" +
//                "C             Bb\n" +
//                "Lay down your arms\n" +
//                "F            C\n" +
//                "Give up the fight\n" +
//                "F   C/E Dm\n" +
//                "One, 21 guns\n" +
//                "C\n" +
//                "Throw up your \n" +
//                "Bb    F       C\n" +
//                "arms into the sky\n" +
//                "        Bb F C\n" +
//                "You and I", "Панк");
//        itemViewModel.addSong("Holiday", "Green Day", "Intro:\n" +
//                "   Fm         C#         Ab         Eb\n" +
//                "e|-------------------------------------------|\n" +
//                "B|--9---9---x-------------13--13--x----------|\n" +
//                "G|--10--10--x--10--10--x--13--13--x--12--12--| x4 \n" +
//                "D|-------------11--11--x-------------13--13--|\n" +
//                "A|-------------------------------------------|\n" +
//                "E|-------------------------------------------|\n" +
//                "\n" +
//                "F   C#   Ab   Eb  x2\n" +
//                "Say hey, cha...\n" +
//                "\n" +
//                "F        C#           Ab      Eb\n" +
//                "Hear the sound of the falling rain\n" +
//                "F        C#         Ab   C     F\n" +
//                "Coming down like an Armageddon flame\n" +
//                "F        C#           Ab      Eb      C\n" +
//                "The shame the ones who died without a name\n" +
//                "F        C#           Ab      Eb\n" +
//                "Hear the dogs howling out of key\n" +
//                "F        C#         Ab   C     F\n" +
//                "To a hymn called \"Faith and Misery\"\n" +
//                "F        C#           Ab      Eb      C\n" +
//                "I plead, the company that lost the war today\n" +
//                "\n" +
//                "Припев:\n" +
//                "        F       C#               Ab              Eb  \n" +
//                "        I beg to dream and differ from the hollow lies\n" +
//                "        F       C#               Ab              C\n" +
//                "        This is the dawning of the rest of our lives\n" +
//                "        On holiday.\n" +
//                "\n" +
//                "Проигрыш:\n" +
//                "   Fm         C#         Ab         Eb\n" +
//                "e|-------------------------------------------|\n" +
//                "B|--9---9---x-------------13--13--x----------|\n" +
//                "G|--10--10--x--10--10--x--13--13--x--12--12--| x4 \n" +
//                "D|-------------11--11--x-------------13--13--|\n" +
//                "A|-------------------------------------------|\n" +
//                "E|-------------------------------------------|\n" +
//                "\n" +
//                "F        C#           Ab      Eb\n" +
//                "Hear the drum pounding out of time\n" +
//                "F        C#         Ab   C     F\n" +
//                "Another protester has crossed the line\n" +
//                "F        C#           Ab      Eb    C\n" +
//                "To find the money's on the other side\n" +
//                "F        C#           Ab    Eb\n" +
//                "Can I get another amen? Amen!\n" +
//                "F        C#         Ab   C     F\n" +
//                "There's a flag wrapped around a score of men\n" +
//                "F        C#           Ab      Eb  C\n" +
//                "A gag, a plastic bag on a monument\n" +
//                "\n" +
//                "Припев:\n" +
//                "        F       C#               Ab              Eb  \n" +
//                "        I beg to dream and differ from the hollow lies\n" +
//                "        F       C#               Ab              C\n" +
//                "        This is the dawning of the rest of our lives\n" +
//                "        On holiday.\n" +
//                "\n" +
//                "Bridge: F | Ab | C# | Bb | Eb | C }x2 \n" +
//                "        F | Ab | Eb | C  | F \n" +
//                "\n" +
//                "Solo:\n" +
//                "E|---------------------------------------------8-----8--|\n" +
//                "B|--6--6--6--8/9----------------8--5-----5--6-----6-----|\n" +
//                "G|----------------8--65--5--5--------5------------------|\n" +
//                "D|------------------------------------------------------|\n" +
//                "A|------------------------------------------------------|\n" +
//                "E|------------------------------------------------------|\n" +
//                "\n" +
//                "E|---------------------------------8--8--12--12~~--13~~-|\n" +
//                "B|--6--6--6--8/9----------------8~----------------------|\n" +
//                "G|----------------8--65--5--5---------------------------|\n" +
//                "D|------------------------------------------------------|\n" +
//                "A|------------------------------------------------------|\n" +
//                "E|------------------------------------------------------|\n" +
//                "\n" +
//                "F        C#           Ab      Eb\n" +
//                "Sieg Heil to the President-Gas-Man\n" +
//                "F        C#         Ab   C     F\n" +
//                "\"Bombs away!\" is your punishment!\n" +
//                "F        C#           Ab  Eb C\n" +
//                "Pulverize the Eiffel Towers \n" +
//                "F        C#           Ab    Eb\n" +
//                "Who criticize your government\n" +
//                "F        C#         Ab   C     F\n" +
//                "Bang! Bang! goes a broken classic\n" +
//                "F        C#           Ab      Eb  C\n" +
//                "Kill all the fags that don't agree\n" +
//                "Triumph by fire\n" +
//                "Setting fires is not a way that's meant for me\n" +
//                "\n" +
//                "Eb | C | Fm\n" +
//                "\n" +
//                "Bridge 2:\n" +
//                "Bb (slide up to) C\n" +
//                "Just cause\n" +
//                "Bb (slide up to) C\n" +
//                "Just cause\n" +
//                "Because we're on holiday\n" +
//                "\n" +
//                "Припев: x2\n" +
//                "        F       C#               Ab              Eb  \n" +
//                "        I beg to dream and differ from the hollow lies\n" +
//                "        F       C#               Ab              C\n" +
//                "        This is the dawning of the rest of our lives\n" +
//                "        On holiday.\n" +
//                "\n" +
//                "This is our lives on holiday....\n" +
//                "\n" +
//                "Outro: F | C# | Ab | Eb }x3 x)\n" +
//                "       F | C# | Eb | C | Fm | F", "Панк");
//        itemViewModel.addSong("American Idiot", "Green Day", "Intro: Ab Db Gb Db Ab Gb }x4 \n" +
//                "\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "Don't wanna be an American idiot.\n" +
//                "                                    Ab Db Gb Db Ab\n" +
//                "Don't want a nation that under the new media.\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "And can you hear the sound of hysteria?\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "The subliminal mindfuck America.\n" +
//                "\n" +
//                "Chorus:\n" +
//                "Db\n" +
//                "Welcome to a new kind of tension.\n" +
//                "Ab\n" +
//                "All across the alien nation.\n" +
//                "Eb                           Ab\n" +
//                "Everything isn't meant to be okay.\n" +
//                "\n" +
//                "Db\n" +
//                "Television dreams of tomorrow.\n" +
//                "Ab\n" +
//                "We're not the ones who're meant to follow.\n" +
//                "Eb\n" +
//                "Convincing them to walk you.\n" +
//                "\n" +
//                "Bridge: Ab Db Gb Db Ab Gb\n" +
//                "        Ab Db Gb Db Ab\n" +
//                "\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "Well maybe I'm the faggot America.\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "I'm not a part of a redneck agenda.\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "Now everybody do the propaganda.\n" +
//                "                          Ab Db Gb Db Ab Gb\n" +
//                "And sing along in the age of paranoia.\n" +
//                "\n" +
//                "Chorus:\n" +
//                "Db\n" +
//                "Welcome to a new kind of tension.\n" +
//                "Ab\n" +
//                "All across the alien nation.\n" +
//                "Eb                           Ab\n" +
//                "Everything isn't meant to be okay.\n" +
//                "\n" +
//                "Db\n" +
//                "Television dreams of tomorrow.\n" +
//                "Ab\n" +
//                "We're not the ones who're meant to follow.\n" +
//                "Eb\n" +
//                "Convincing them to walk you.\n" +
//                "\n" +
//                "Bridge: Ab Db Gb Db Ab Gb }\n" +
//                "        Ab Db Gb Db Ab    } x2\n" +
//                "\n" +
//                "Solo:\n" +
//                "E|----------------------------------------------------------------------|\n" +
//                "B|----------------------------------------------------------------------|\n" +
//                "G|--10-10-10-10-10-10-8--6-----8-8-8-8--10-8--6--5--3-3-3-3h5-6-53-3----|\n" +
//                "D|--x--x--x--x--x--x--x--x-----x-x-x-x--x--x--x--x----------------------|\n" +
//                "A|--8--8--8--8--8--8--6--4-----6-6-6-6--8--6--4--3----------------------|\n" +
//                "E|----------------------------------------------------------------------|\n" +
//                "\n" +
//                "E|---------------------------8--------11--------|\n" +
//                "B|--------9--------11------------------------9--|\n" +
//                "G|--8--8-----8--8------8--8-----8--8------8-----|\n" +
//                "D|----------------------------------------------|\n" +
//                "A|----------------------------------------------|\n" +
//                "E|----------------------------------------------|\n" +
//                "\n" +
//                "E|--------------------------------------------------------------------------|\n" +
//                "B|-----------------------------------------------------------------------9--|\n" +
//                "G|--10-10-10-10-10-10-8--6-----8-8-8-8--10-8--6--5--3-3-3-3h5-6-53-3/8------|\n" +
//                "D|--x--x--x--x--x--x--x--x-----x-x-x-x--x--x--x--x--------------------------|\n" +
//                "A|--8--8--8--8--8--8--6--4-----6-6-6-6--8--6--4--3--------------------------|\n" +
//                "E|--------------------------------------------------------------------------|\n" +
//                "\n" +
//                "Bridge: Ab Db Gb Db Ab Gb \n" +
//                "        Ab Db Gb Db Ab    \n" +
//                "\n" +
//                "Ab       Db   Gb    Db   Ab   Gb\n" +
//                "Don't wanna be an American idiot.\n" +
//                "Ab      Db      Gb    Db     Ab\n" +
//                "One nation controlled by the media.\n" +
//                "Ab       Db   Gb    Db   Ab  Gb\n" +
//                "Information nation of hysteria.\n" +
//                "(no chords)\n" +
//                "It's going out to idiot America.\n" +
//                "\n" +
//                "Chorus:\n" +
//                "Db\n" +
//                "Welcome to a new kind of tension.\n" +
//                "Ab\n" +
//                "All across the alien nation.\n" +
//                "Eb                           Ab\n" +
//                "Everything isn't meant to be okay.\n" +
//                "\n" +
//                "Db\n" +
//                "Television dreams of tomorrow.\n" +
//                "Ab\n" +
//                "We're not the ones who're meant to follow.\n" +
//                "Eb\n" +
//                "Convincing them to walk you.", "Панк");

        ImageView filterButton = view.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.filterFragment);
            }
        });

        EditText searchEditText = view.findViewById(R.id.editTextSearch);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemViewModel.search(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Не используется
            }


        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (DataHolder.getFiltered()) {
            selectedGenres = DataHolder.getData();
            itemViewModel.filterSongsByGenres(selectedGenres);
            DataHolder.setFiltered(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemViewModel.CloseDB();
    }

}
