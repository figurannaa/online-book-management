# Online könyvkezelő rendszer
Az alábbi program a *Programozás módszertana II.* kurzus beadandója.

## Használt technológiák
Az ajánlott technikákat alkalmaztam a program elkészítéséhez, melyek
* Thymeleaf
* Bootstrap 5
* Springboot

Egyetlen kivétel a **MySQL Workbench**, mivel ez már telepítve volt a gépemre, ezért alkalmaztam ezt az adatbázis kezelésére **XAMPP** helyett.
## Fejlesztés
A fejlesztés alatt igyekeztem az órán elkészített CarBasicAPI felépítését követni. Azon funkciókat mutatom be melyekhez nem kaptunk pontos utasításokat a feladat leírásban.
### Könyv hozzáadása a saját könyveim listához
**Hozzáadás** esetén egy **MyBook** entitást hoz létre a program, mely tartalmaz egy ID-t és az adott könyvet.

A gombra kattintva megjelenik a törléshez hasonló módon megjelenik egy **megerősítő fül**. Ennek elfogadása után kerül csak hozzá a saját listához adáshoz.
* A törlés megerősítő ablakjának felépítését másoltam, majd egészítettem ki egy láthatatlan ```<form>``` mezővel, melyben megadtam a **POST** kéréshez szükséges könyv ID-ját.
* Ezen könyv ID alapján a **Service** réteg megkeresi az adott könyvet és létrehozza a **MyBook** entitást. 
### Statisztika listázása
A szükséges négy mezőhöz külön hoztam létre metódusokat, melyeket különálló ```model.addAttribute()``` segítségével állítottam be az **index.html** oldalon.

Minden metódusra külön készült aspektus orientált loggolás is.
## Loggolás
Az órán tanult módszert alkalmaztam a logok-hoz szükséges metódusok létrehozásában. Mivel ezen része a programnak a fejlesztés későbbi szakaszában lett implementálva, ezért megeshet, hogy nem minden eset van jelen a **logger.log** fájlban. A **logger_old.log** fájl tartalmaz olyan logokat, melyek a fejlesztés folyamata alatt kerültek bele. Látható például, hogy mikor adtam hibaüzenetet ahhoz az esethez amikor olyan könyvet szeretne a felhasználó hozzáadni a saját listához, mely már szerepel azon. 
## Kész program megjelenése