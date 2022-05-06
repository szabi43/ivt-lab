package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary = mock(TorpedoStore.class);
  private TorpedoStore secondary = mock(TorpedoStore.class);


  @BeforeEach
  public void init(){
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1); 
  }

  @Test
  public void fireTorpedo_Single_Success_Primary_Empty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).fire(1); 
  }

  @Test 
  public void fireTorpedo_Single_Failure(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();  
  }

  @Test 
  public void fireTorpedo_All_Success_Primary_Empty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).fire(1);  
  }

  @Test 
  public void fireTorpedo_All_Success_Secondary_Empty(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).fire(1); 
    verify(secondary, times(1)).isEmpty();   
  }

  @Test 
  public void fireTorpedo_All_Failure(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();   
  }

  @Test  
  public void fireTorpedo_Single_Success_Secondary_Isnt_Fired(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

}
