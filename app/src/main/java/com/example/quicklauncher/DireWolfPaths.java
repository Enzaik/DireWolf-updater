package com.example.quicklauncher;

public class DireWolfPaths {

    public String getDirewolf(int id){

        String urls[] = {"https://sourceforge.net/projects/direwolf/files/Unified/DireWolf_v7.4_TPFinal_Unified_03-04-1104.zip",
        "https://sourceforge.net/projects/direwolf/files/Murali/DireWolf_v4.7_TPFinal_Murali_03-04-10.55.zip"
        };
        return urls[id];

    }

}
