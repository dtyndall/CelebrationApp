//
//  Event.m
//  jsontest
//
//  Created by Chirag Patel on 1/16/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "Event.h"

@implementation Event
@synthesize eventID, eventAuthor, eventCategory, eventDesc, eventName, eventSurvey, eventTrack;

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
