# Notes for designing teleport function

Teleport is a movement with constrains as below:

- Basic Constrains:
  - Inside the board (ValorLayout.getBreadth(),ValorLayout.getLength())
  - Not on the same lane
  - Not inaccessible
  - No other heroes (ValorLayout.getCell.isHeroPresent())
  - No crossing the monsters in the lane (iterate the upper cells of the lane to see if there are monsters)
  - Jump to a particular hero.
  - No Jumping to random location
