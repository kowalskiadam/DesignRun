insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Coach", "coach1", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Coach", "coach2", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Coach", "coach3", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Coach", "coach4", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Coach", "coach5", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Athlete", "athlete1", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Athlete", "athlete2", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Athlete", "athlete3", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Athlete", "athlete4", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")
insert into users (USER_TYPE, login, password, aboutMeShort, firstName, lastName) values ("Athlete", "athlete5", "$2a$10$vr7uHy7yZvFOMcKUlagrM.CwiRSPaEZr9ZrcLvQSieogXyMz1kjKe", "", "", "")

insert into methods (shortDescription, name, hide, owner_id) values ("klasyczna metoda Danielsa", "Metoda Danielsa", false, 1);
insert into methods (shortDescription, name, hide, owner_id) values ("własne metoda testowa", "Bieganie 7.0", false,1);
insert into methods (shortDescription, name, hide, owner_id) values ("ukryta metoda testowa", "Bieganie 7.0", true, 1);
insert into methods (shortDescription, name, hide, owner_id) values ("własne metoda testowa innego trenera", "Bieganie 7.1", false, 2);

insert into coaches_athletes (coaches_id, athletes_id) values (1, 6);
insert into coaches_athletes (coaches_id, athletes_id) values (1, 7);
insert into coaches_athletes (coaches_id, athletes_id) values (1, 8);
insert into coaches_athletes (coaches_id, athletes_id) values (2, 9);
insert into coaches_athletes (coaches_id, athletes_id) values (2, 6);

insert into potential_coaches_athletes (potentialCoaches_id, potentialAthletes_id) values (1, 9);
insert into potential_coaches_athletes (potentialCoaches_id, potentialAthletes_id) values (2, 10);
insert into potential_coaches_athletes (potentialCoaches_id, potentialAthletes_id) values (1, 10);

insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("bieg spokojny", "easy run", "BS", 5, 15, 1, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("wybieganie", "the long one", "Long", 15, 32, 1, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("tempo progowe", "hard stuff", "Tempo", 10, 20, 1, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("przebieżka", "short and easy","BS", 5, 15, 2, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("wybieganie","very long", "Long", 15, 25, 2, false);
insert into training_types (name, description, shortCut, minDistance, maxDistance, method_id, hide) values ("zabawa biegowa","run fast for fun" ,"Zabawa", 8, 16, 2, false);

insert into plans (name, startDay, weeksNumber, athlete_id, coach_id, method_id) values ("test plan", 20190812, 3, 6, 1, 1);
insert into plans (name, startDay, weeksNumber, athlete_id, coach_id, method_id) values ("test plan2", 20190819, 2, 7, 1, 2);
insert into plans (name, startDay, weeksNumber, athlete_id, coach_id, method_id) values ("test plan nie dla id1", 20190812, 3, 9, 2, 1);

insert into weeks (orderInPlan, plan_id) values (1,1);
insert into weeks (orderInPlan, plan_id) values (2,1);
insert into weeks (orderInPlan, plan_id) values (3,1);

insert into days (date, dayOfWeek, plan_id, week_id) values (20190812, 1, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190813, 2, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190814, 3, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190815, 4, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190816, 5, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190817, 6, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190818, 7, 1, 1);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190819, 1, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190820, 2, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190821, 3, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190822, 4, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190823, 5, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190823, 6, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190824, 7, 1, 2);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190825, 1, 1, 3);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190826, 2, 1, 3);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190827, 3, 1, 3);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190828, 4, 1, 3);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190829, 5, 1, 3);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190830, 6, 1, 3);
insert into days (date, dayOfWeek, plan_id, week_id) values (20190831, 7, 1, 3);

insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values ("", "trening", 10, "Bieg spokojny", 1, "BS 10", 1, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values ("", "trening", 12, "Akcent", 1, "A 12", 2, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback ) values ("", "trening", 10, "Bieg spokojny", 1, "BS 10", 3, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 10, "Bieg spokojny", 1, "BS 10", 5, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 13, "Bieg spokojny", 1, "BS 13", 6, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback ) values (" ", "trening", 10, "Bieg spokojny", 1, "BS 10", 8, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback ) values (" ", "trening", 9, "Bieg spokojny", 1,"BS 10", 9, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 10, "Bieg spokojny", 2, "BS 10", 9, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 10, "Bieg spokojny", 1, "BS 10", 11, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 12, "Akcent", 1, "BS 12", 14, 2, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 10, "Bieg spokojny", 1, "BS 10", 15, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 10, "Bieg spokojny", 2, "BS 10", 15, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 5, "Bieg spokojny", 1, "BS 10", 17, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 10, "Bieg spokojny", 1, "BS 10", 19, 1, false, "");
insert into tranings (athleteComment, description, distance, name, orderInDay, shortCut, day_id, trainingType_id, importantChanges, coachFeedback) values (" ", "trening", 14, "Akcent 14", 1, "A 14", 21, 2, false, "");