//
//  EventDetailViewController.h
//  jsontest2
//
//  Created by Chirag Patel on 1/17/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface EventDetailViewController : UIViewController

@property (nonatomic, strong) IBOutlet UILabel *NameLabel;
@property (nonatomic, strong) IBOutlet UILabel *AuthorLabel;
@property (nonatomic, strong) IBOutlet UILabel *DescLabel;
@property (nonatomic, strong) IBOutlet UILabel *CategoryLabel;
@property (nonatomic, strong) IBOutlet UILabel *TrackLabel;
@property (nonatomic, strong) IBOutlet UILabel *SurveyLabel;

@property (nonatomic, strong) NSMutableArray *EventModal;

@end
