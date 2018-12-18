package com.kharsh;

public class RecordOcenka {
    private String login;
    private String date;
    private String topic;
    private String ocenka;
    private String maxcount;
    private String battleRegime;
    public RecordOcenka(String login, String date, String topic, String ocenka, String maxcount, String battleRegime){
        this.login = login;
        this.date = date;
        this.topic = topic;
        this.ocenka = ocenka;
        this.maxcount = maxcount;
        this.battleRegime = battleRegime;
    }

    public String getLogin(){
        return(login);
    }
    public String getDate(){
        return(date);
    }
    public String getTopic(){
        return(topic);
    }
    public String getOcenka(){
        return(ocenka);
    }
    public String getMaxCount(){
        return(maxcount);
    }
    public String getbattleRegime(){
        return(battleRegime);
    }
}
