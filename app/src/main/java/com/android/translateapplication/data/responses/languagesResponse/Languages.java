package com.android.translateapplication.data.responses.languagesResponse;

import com.android.translateapplication.data.room.LanguagesEntities;

import java.util.ArrayList;

public class Languages {

    LanguagesEntities af;
    LanguagesEntities am;
    LanguagesEntities ar;
    LanguagesEntities as;
    LanguagesEntities az;
    LanguagesEntities bg;
    LanguagesEntities bn;
    LanguagesEntities bs;
    LanguagesEntities ca;
    LanguagesEntities cs;
    LanguagesEntities cy;
    LanguagesEntities da;
    LanguagesEntities de;
    LanguagesEntities el;
    LanguagesEntities en;
    LanguagesEntities es;
    LanguagesEntities et;
    LanguagesEntities fa;
    LanguagesEntities fi;
    LanguagesEntities fil;
    LanguagesEntities fj;
    LanguagesEntities fr;
    LanguagesEntities ga;
    LanguagesEntities gu;
    LanguagesEntities he;
    LanguagesEntities hi;
    LanguagesEntities hr;
    LanguagesEntities ht;
    LanguagesEntities hu;
    LanguagesEntities hy;
    LanguagesEntities id;
    LanguagesEntities is;
    LanguagesEntities it;
    LanguagesEntities iu;
    LanguagesEntities ja;
    LanguagesEntities kk;
    LanguagesEntities km;
    LanguagesEntities kmr;
    LanguagesEntities kn;
    LanguagesEntities ko;
    LanguagesEntities ku;
    LanguagesEntities lo;
    LanguagesEntities lt;
    LanguagesEntities lv;
    LanguagesEntities mg;
    LanguagesEntities mi;
    LanguagesEntities ml;
    LanguagesEntities mr;
    LanguagesEntities ms;
    LanguagesEntities mt;
    LanguagesEntities mww;
    LanguagesEntities my;
    LanguagesEntities nb;
    LanguagesEntities ne;
    LanguagesEntities nl;
    LanguagesEntities or;
    LanguagesEntities otq;
    LanguagesEntities pa;
    LanguagesEntities pl;
    LanguagesEntities prs;
    LanguagesEntities ps;
    LanguagesEntities pt;
    LanguagesEntities ro;
    LanguagesEntities ru;
    LanguagesEntities sk;
    LanguagesEntities sl;
    LanguagesEntities sm;
    LanguagesEntities sq;
    LanguagesEntities sv;
    LanguagesEntities sw;
    LanguagesEntities ta;
    LanguagesEntities te;
    LanguagesEntities th;
    LanguagesEntities ti;
    LanguagesEntities to;
    LanguagesEntities tr;
    LanguagesEntities ty;
    LanguagesEntities uk;
    LanguagesEntities ur;
    LanguagesEntities vi;
    LanguagesEntities yua;
    LanguagesEntities yue;

    public ArrayList<LanguagesEntities> getLanguages() {

        ArrayList<LanguagesEntities> languages = new ArrayList<>();

        addLanguages(languages);

        return languages;
    }

    private  void addLanguages(ArrayList<LanguagesEntities> languages) {

        convert(languages,af,"af");
        convert(languages,am,"am");
        convert(languages,ar,"ar");
        convert(languages,as,"as");
        convert(languages,az,"az");
        convert(languages,bg,"bg");
        convert(languages,bn,"bn");
        convert(languages,bs,"bs");
        convert(languages,ca,"ca");
        convert(languages,cs,"cs");
        convert(languages,cy,"cy");
        convert(languages,da,"da");
        convert(languages,de,"de");
        convert(languages,el,"el");
        convert(languages,en,"en");
        convert(languages,es,"es");
        convert(languages,et,"et");
        convert(languages,fa,"fa");
        convert(languages,fi,"fi");
        convert(languages,fil,"fil");
        convert(languages,fj,"fj");
        convert(languages,fr,"fr");
        convert(languages,ga,"ga");
        convert(languages,gu,"gu");
        convert(languages,he,"he");
        convert(languages,hi,"hi");
        convert(languages,hr,"hr");
        convert(languages,ht,"ht");
        convert(languages,hu,"hu");
        convert(languages,hy,"hy");
        convert(languages,id,"id");
        convert(languages,is,"is");
        convert(languages,it,"it");
        convert(languages,iu,"iu");
        convert(languages,ja,"ja");
        convert(languages,kk,"kk");
        convert(languages,km,"km");
        convert(languages,kmr,"kmr");
        convert(languages,kn,"kn");
        convert(languages,ko,"ko");
        convert(languages,ku,"ku");
        convert(languages,lo,"lo");
        convert(languages,lt,"lt");
        convert(languages,lv,"lv");
        convert(languages,mg,"mg");
        convert(languages,mi,"mi");
        convert(languages,ml,"ml");
        convert(languages,mr,"mr");
        convert(languages,ms,"ms");
        convert(languages,mt,"mt");
        convert(languages,mww,"mww");
        convert(languages,my,"my");
        convert(languages,nb,"nb");
        convert(languages,ne,"ne");
        convert(languages,nl,"nl");
        convert(languages,or,"or");
        convert(languages,otq,"otq");
        convert(languages,pa,"pa");
        convert(languages,pl,"pl");
        convert(languages,prs,"prs");
        convert(languages,ps,"ps");
        convert(languages,pt,"pt");
        convert(languages,ro,"ro");
        convert(languages,ru,"ru");
        convert(languages,sk,"sk");
        convert(languages,sl,"sl");
        convert(languages,sm,"sm");
        convert(languages,sq,"sq");
        convert(languages,sv,"sv");
        convert(languages,sw,"sw");
        convert(languages,ta,"ta");
        convert(languages,te,"te");
        convert(languages,th,"th");
        convert(languages,ti,"ti");
        convert(languages,to,"to");
        convert(languages,tr,"tr");
        convert(languages,ty,"ty");
        convert(languages,uk,"uk");
        convert(languages,ur,"ur");
        convert(languages,vi,"vi");
        convert(languages,yua,"yua");
        convert(languages,yue,"yue");
    }

    void  convert(ArrayList<LanguagesEntities> languages,LanguagesEntities entities,String st){
        entities.setCode(st);
        languages.add(entities);
    }

}


