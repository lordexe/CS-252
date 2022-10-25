//
// This example program was adapted from an example program provided with
//
//     Horstmann & Cornell
//     _Core Java, Volume 1 - Fundamentals_
//     Prentice Hall
//

// 
// Project phase 1
// team member: Anirudh Chauhan, Owen Gruenwald, Phat Tu
//

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;


public class BetterMouseTest
{

    public static void main(String [] commandLineArguments)
    {

        EventQueue.invokeLater(
            () ->
                {

                    ProgramFrame frame = new ProgramFrame();

                    frame.setTitle("Better Mouse Test");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                    }
            );

        }

    }


class ProgramFrame extends JFrame
{

    public ProgramFrame()
    {

        add(new MouseComponent());
        pack();

        }

    }


class MouseComponent extends JComponent
{

    private static final int OUR_DEFAULT_WIDTH = 400;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private static final int OUR_SQUARE_SIDE_LENGTH = 30;

    private ArrayList< Rectangle2D > mySquares;
    private Rectangle2D myCurrentSquare;

    private ArrayList< Rectangle2D > squares()
    {

        return mySquares;

        }

    private Rectangle2D currentSquare()
    {

        return myCurrentSquare;

        }

    private void setSquares(ArrayList< Rectangle2D > other)
    {

        mySquares = other;

        }

    private void setCurrentSquare(Rectangle2D other)
    {

        myCurrentSquare = other;

        }

    public MouseComponent()
    {

        setSquares(new ArrayList< Rectangle2D >());
        setCurrentSquare(null);

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());

        }

    @Override
    public Dimension getPreferredSize()
    {

        return new Dimension(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        }


    @Override
    public void paintComponent(Graphics canvas)
    {

        for (Rectangle2D squareOnCanvas : squares())
            ((Graphics2D) canvas).draw(squareOnCanvas);

        }

    public Rectangle2D findSquareContainingPoint(Point2D clickPoint)
    {

        int squareNumber;

        //
        // Changed this, comment here @Kevin
        //

        for (squareNumber = squares().size() - 1;
                squareNumber > -1
                    && ! squares().get(squareNumber).contains(clickPoint);
                --squareNumber)
                //
                // inv:
                //     No square in squares()[0 ... squareNumber - 1] spatially contains clickpoint
                //
            ; // do nothing


        if (squareNumber > -1)
            return squares().get(squareNumber);
        else 
            return null;

        }

    public void placeAdditionalSquare(Point2D clickPoint)
    {

        setCurrentSquare(
            new Rectangle2D.Double(
                clickPoint.getX() - OUR_SQUARE_SIDE_LENGTH / 2,
                clickPoint.getY() - OUR_SQUARE_SIDE_LENGTH / 2,
                OUR_SQUARE_SIDE_LENGTH,
                OUR_SQUARE_SIDE_LENGTH
                )
            );

        squares().add(currentSquare());

        repaint();

        // Change the cursor to cross hair as soon as the square is placed on the screen.
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        System.out.println(
            "New square placed at (" + clickPoint.getX() +", " + clickPoint.getY() + ")"
            );

        }

    public void removeExistingSquare(Rectangle2D existingSquare)
    {

        if (existingSquare != null) {

            if (existingSquare == currentSquare())
                setCurrentSquare(null);

            squares().remove(existingSquare);

            
            
            // Change cursor to default as soon as the square is removed.
            

            System.out.println("Existing square removed");

            }

        }


    private class MouseHandler extends MouseAdapter
    {

        boolean blankSpace = true;

        int myPresses = 0;
        int myClicks = 0;

        @Override
        public void mousePressed(MouseEvent event)
        {

            ++ myPresses;
            System.out.println(
                "Mouse press " + myPresses + " at (" + event.getX() + ", " + event.getY() + ")"
                );

            setCurrentSquare(findSquareContainingPoint(event.getPoint()));

            // Commented  the if statement because we're checking and adding the squares in a separate
            // if statement in public void mouseClicked(MouseEvent event).

            // if (currentSquare() == null)
            //    placeAdditionalSquare(event.getPoint());

            }

        @Override
        public void mouseClicked(MouseEvent event)
        {

            int currentClickCounts = event.getClickCount();

            if (currentClickCounts % 3 == 0)
                currentClickCounts = 3;
            else 
                currentClickCounts = event.getClickCount() % 3;
            
            ++ myClicks;
            if (event.getClickCount() < 2){
                System.out.println(
                    "Mouse click " + myClicks + " at (" + event.getX() + ", " + event.getY() + ")"
                    );
                if (findSquareContainingPoint(event.getPoint()) != null)
                    blankSpace = false;
                else
                    blankSpace = true;
                    
            } 
            else
                System.out.println(
                    "Mouse double-click " + myClicks + " at (" + event.getX() + ", " + event.getY() + ")"
                    );

            setCurrentSquare(findSquareContainingPoint(event.getPoint()));

            if (blankSpace == true){

                // if its the first click then create square

                if (currentClickCounts == 1){
                    System.out.println("Testing 1" + event.getClickCount());
                    placeAdditionalSquare(event.getPoint());
                }
                // if 3rd click then remove existing square
                else if (currentClickCounts == 3){
                    removeExistingSquare(currentSquare());
                    System.out.println("Testing 2" + event.getClickCount());
                    repaint();
                    setCurrentSquare(findSquareContainingPoint(event.getPoint()));
                    if (currentSquare() == null){
                        setCursor(Cursor.getDefaultCursor());
                        }
                    else {
                        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        }
                    }

                }

            else {
                // if its the second click, remove the square
                // 
                // line 286 - 293, 267 - 274
                // define if cursor still on any square by " setCurrentSquare(findSquareContainingPoint(event.getPoint()));"
                // then define cursor base on the currentSquare result
                //
                if (currentClickCounts == 2){
                    removeExistingSquare(currentSquare());
                    System.out.println("Testing 3" + event.getClickCount());
                    repaint();
                    setCurrentSquare(findSquareContainingPoint(event.getPoint()));
                    if (currentSquare() == null){
                        setCursor(Cursor.getDefaultCursor());
                        }
                    else {
                        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        }
                }

                // if its the third click, place the square
                else if (currentClickCounts == 3)
                    placeAdditionalSquare(event.getPoint());

            }
        }

    }


    private class MouseMotionHandler implements MouseMotionListener
    {

        int myMoves = 0;
        int myDrags = 0;

        @Override
        public void mouseMoved(MouseEvent event)
        {

            ++ myMoves;
            System.out.println(
                "Mouse move " + myMoves + " to (" + event.getX() + ", " + event.getY() + ")"
                );

            if (findSquareContainingPoint(event.getPoint()) == null)
                setCursor(Cursor.getDefaultCursor());
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

            }

        @Override
        public void mouseDragged(MouseEvent event)
        {

            ++ myDrags;
            System.out.println(
                "Mouse drag " + myDrags + " to (" + event.getX() + ", " + event.getY() + ")"
                );

            if (currentSquare() != null) {

                currentSquare().setFrame(
                    event.getX() - OUR_SQUARE_SIDE_LENGTH / 2,
                    event.getY() - OUR_SQUARE_SIDE_LENGTH / 2,
                    OUR_SQUARE_SIDE_LENGTH,
                    OUR_SQUARE_SIDE_LENGTH
                    );
                

                //
                // get the Index of current Square from Square array
                // the currSquare will add in square so findSquareContainingPoint will find the latest one
                // update the array of Square so their no duplicate error
                //

                int thisIndex = squares().indexOf(currentSquare());

                for (int index=(squares().size() - 1); index >= 0; --index){
                    
                    if (index > thisIndex & currentSquare().intersects(squares().get(index))){
                        
                        squares().add(currentSquare());
                        squares().remove(thisIndex);

                    }
                }

                repaint();

                }

            }

        }

    }
