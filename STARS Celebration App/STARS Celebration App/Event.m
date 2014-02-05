//
//  Event.m
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "Event.h"

@implementation Event
//allows use of vars without "_" prefix
@synthesize eventID, eventAuthor, eventCategory, eventDesc, eventName, eventSurvey, eventTrack;

//Init method
-(id) initWithEventID: (NSString *) eID andEventName: (NSString *) eName andEventAuthor: (NSString *) eAuthor andEventCategory: (NSString *) eCat andEventTrack: (NSString *) eTrack andEventSurvey: (NSString *) eSurvey andEventDesc: (NSString *) eDesc;
{
    self = [super init];
    if (self)
    {
        eventID = eID;
        eventName = eName;
        eventAuthor = eAuthor;
        eventDesc = eDesc;
        eventCategory = eCat;
        eventSurvey = eSurvey;
        eventTrack = eTrack;
    }
    return self;
}

@end
