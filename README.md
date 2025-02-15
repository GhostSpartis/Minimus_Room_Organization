# My Personal Project - Minimus
## Room Organization Done Right

The **main objective** of this application is to help the user 
**organize their room through the philosophical lens of Minimalism** the application will allow you
to inventory a list of items and their description of their value to you as well as their value in CAD into a room.

*Possible features include:*
- Categorizing items within a room
- displaying icons for each category
- Having multiple Rooms
- Include photo of the item once clicked on
- list of removed items
- Statistics on which item category is most likely to be removed and kept
- Ranking of the items based on a ten point system + in-depth description of value once clicked on
- marking items for sale 
- adding up marked items to see possible final value in CAD
- Graphical *My Room* box representation to place item within (based on given square foot)

## Who is this For ?
This is meant to cater to people who want to change and **clean up their messy room in a more organized and
systematic manor** in a way that will not only **improve their environment** but **will also help paint a clearer picture 
of the type of things that are important and hold value within their lives**

## Why am I interested in this topic
Recently Ive gotten really into the psychology and self-help books, this has taught me that one of the most
important things when it comes to growing as a person and achieving what you want is having a clear picture and a clear mind.
This led me down the path of philosophy through the book , the daily stoic, as well as digging into the values of stoicism 
and later on minimalism. Long story short, a person's environment is quintessential when it comes to clearing one's mind
, as having cluttered room is the same as cluttering your brain. So since my room is pretty cluttered with a lot of stuff 
right now I thought it may help me and other people who want to clear up space and grow achieve this goal in a more palatable
way.

## User Stories
- As a user, I want to be able to add an item to a room
- As a user, I want to be able to view a list of items in my room
- As a user, I want to be able to mark an item as up for sale
- As a user, I want to be able to remove items from my room
- As a user, I want to be able to rate an item's value from 1-10 
- As a user, I want to be able to see my items sorted based on rating
- As a user, I want to be able to see the total for the items I put on sale
- As a user, I want to be able to multi-remove all on sale items in my room
- As a user, I want to be able save my current room
- As a user, I want to be able to load my current room
- As a user, I want to be able to add multiple Items to a Room
- As a user, I want to be able to load and save the state of the application

# Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Items to a Room" by
   Clicking on add Item, fill in the values as specified,
   once completed, click **create item** to create an item inside the room
- To View created Items, click **Return Home** then click View Items, this shows an updating number on the price of
  items on sale as well as a remove item functionality
- To Remove an Item go to **View Items** type in the textfield at the bottom
   the rank of the item, click on remove Item
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
  changing the variables in the add item panel then clicking *create Item* as many times as you please
  to view your items, click return home then view items
- To Sort Items, click on **Sort Items From Highest to lowest**
- To Remove all items on sale, click on **remove sold items**
- You can save the state of my application by clicking save at the home screen
- You can reload the state of my application by clicking load at the home screen

Phase 4: Task 2
---
```
Thu Nov 30 22:24:05 PST 2023
Room Has Been Opened
Thu Nov 30 22:24:37 PST 2023
Laptop Has Been added to Room
Thu Nov 30 22:25:03 PST 2023
Hunting Horn Has Been added to Room
Thu Nov 30 22:25:13 PST 2023
Laptop Has Been Removed from Room
Thu Nov 30 22:25:19 PST 2023
Room Has Been Saved
Thu Nov 30 22:25:21 PST 2023
Room Has Been Loaded
Thu Nov 30 22:25:21 PST 2023
Room Has Been Opened
Thu Nov 30 22:25:21 PST 2023
Hunting Horn Has Been added to Room
Thu Nov 30 22:25:23 PST 2023
Items In Room Have Been Sorted
Thu Nov 30 22:25:28 PST 2023
Hunting Horn Has Been Removed from Room
Thu Nov 30 22:25:28 PST 2023
Items On Sale Have Been Removed
```

# Phase 4: Task 3
- While Designing my GUI Panel I noticed that alot of the same labels and button creation was repeated over and over for
  each panel, each with different functions. I could refactor the code of button creation to a new class via a 
  composite pattern, so that the body of the JPanels are alot more cohesive and readable while reducing duplication in 
  the GUI Class

- Another Thing I can fix with my code is the amount of responsibility each function has in RoomGUI button behaviours,
  right now each behaviour plays a couple different functions and object initializations which can be made more readable 
  if I were to refactor each step of the behaviour into its own class allowing me to not only cut down on the 
  duplicaiton of code but also makes each behaviour more human readable and can easily be explained by looking through
  the behaviour

- A third thing that can be worked on is applying composite design to the room's finditems and gettotalPrice and 
  removsolditems functions as they all use the same template of running through a list with an accumulator with 
  different inner bodies, that way I can make the code more readable and reduces duplication
    