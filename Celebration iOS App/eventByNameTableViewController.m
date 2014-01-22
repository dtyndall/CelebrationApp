//
//  eventByNameTableViewController.m
//  jsontest2
//
//  Created by Chirag Patel on 1/16/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "eventByNameTableViewController.h"
#import "TableCell.h"
#import "EventDetailViewController.h"

@interface eventByNameTableViewController ()


@end

#define getAllEventsURL @"http://starscomputingcorps.org/celebrationApp/getAllEvents.php"

@implementation eventByNameTableViewController
@synthesize json, eventsArray;
- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    [self retrieveData];
}

#pragma mark - Methods
- (void) retrieveData
{
    NSURL * url = [NSURL URLWithString:getAllEventsURL];
    NSData * data = [NSData dataWithContentsOfURL:url];
    NSDictionary *temp = [[NSDictionary alloc] init];
    
    temp = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:nil];
    json = [temp objectForKey:@"events"];
    eventsArray = [[NSMutableArray alloc] init];
    
    for (int i = 0; i < json.count; i++) {
        //Create event objects
        NSString * eID = [[json objectAtIndex:i] objectForKey:@"event_id"];
        NSString * eName = [[json objectAtIndex:i] objectForKey:@"event_name"];
        NSString * eAuthor = [[json objectAtIndex:i] objectForKey:@"author_name"];
        NSString * eDesc = [[json objectAtIndex:i] objectForKey:@"event_description"];
        NSString * eCat = [[json objectAtIndex:i] objectForKey:@"event_category"];
        NSString * eSurvey = [[json objectAtIndex:i] objectForKey:@"survey"];
        NSString * eTrack = [[json objectAtIndex:i] objectForKey:@"track"];
        
        Event * myEvent = [[Event alloc] initWithEventID:eID andEventName:eName andEventAuthor:eAuthor andEventCategory:eCat andEventTrack:eTrack andEventSurvey:eSurvey andEventDesc:eDesc];
        
        //Adds event to events array
        [eventsArray addObject:myEvent];
    }
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return eventsArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"TableCell";
    TableCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    // Configure the cell...
    
    int row = [indexPath row];
    Event *temp = eventsArray[row];
    cell.TitleLabel.text = temp.eventName;
    
    
    return cell;
}


/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/


#pragma mark - Navigation

// In a story board-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    if ([[segue identifier] isEqualToString:@"ShowDetails"]) {
        EventDetailViewController *detailviewcontroller = [segue destinationViewController];
        
        NSIndexPath *myIndexPath = [self.tableView indexPathForSelectedRow];
        int row = [myIndexPath row];
        detailviewcontroller.EventModal = [eventsArray objectAtIndex:row];
    }
}

@end
