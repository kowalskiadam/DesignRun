insert into coaches (login) values ("chudy");
insert into coaches (login) values ("daniels");
insert into coaches (login) values ("adam");
insert into coaches (login) values ("jaro");
insert into coaches (login) values ("maciekj");

insert into methods (description, name, hide, owner_id) values ("klasyczna metoda Danielsa", "Metoda Danielsa", false, 1);
insert into methods (description, name, hide, owner_id) values ("własne metoda testowa", "Bieganie 7.0", false,1);
insert into methods (description, name, hide, owner_id) values ("ukryta metoda testowa", "Bieganie 7.0", true, 1);
insert into methods (description, name, hide, owner_id) values ("własne metoda testowa innego trenera", "Bieganie 7.1", false, 2);

insert into athletes (login, firstName, lastName) values ("zebra", "karol", "chodkiewicz");
insert into athletes (login, firstName, lastName) values ("slon", "anna", "jagielonka");
insert into athletes (login, firstName, lastName) values ("zubr", "maksymilian", "wielki");
insert into athletes (login, firstName, lastName) values ("tygrys", "tytus", "wspanialy");
insert into athletes (login, firstName, lastName) values ("drozd", "ferdynand", "luksemburski");

insert into coaches_athletes (coaches_id, athletes_id) values (1, 1);
insert into coaches_athletes (coaches_id, athletes_id) values (1, 2);
insert into coaches_athletes (coaches_id, athletes_id) values (1, 3);
insert into coaches_athletes (coaches_id, athletes_id) values (2, 4);
insert into coaches_athletes (coaches_id, athletes_id) values (2, 1);

insert into potential_coaches_athletes (potentialCoaches_id, potentialAthletes_id) values (1, 4);
insert into potential_coaches_athletes (potentialCoaches_id, potentialAthletes_id) values (2, 5);
insert into potential_coaches_athletes (potentialCoaches_id, potentialAthletes_id) values (1, 5);

insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("bieg spokojny", "easy run", "BS", 5, 15, 1, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("wybieganie", "the long one", "Long", 15, 32, 1, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("tempo progowe", "hard stuff", "Tempo", 10, 20, 1, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("przebieżka", "short and easy","BS", 5, 15, 2, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("wybieganie","very long", "Long", 15, 25, 2, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("zabawa biegowa","run fast for fun" ,"Zabawa", 8, 16, 2, false);
