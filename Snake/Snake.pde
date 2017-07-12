final color FIELD_COLOR = color(32);
final color FIELD_STROKE_COLOR = color(0);
final float FIELD_STROKE_WEIGHT = 1;

final color FIELD_GRID_COLOR = color(0);
final float FIELD_GRID_WEIGHT = 1;

final int FIELD_WIDTH = 20,
          FIELD_HEIGHT = 20;

final color APPLE_COLOR = color(255, 0, 0);
final color APPLE_STROKE_COLOR = color(0);
final float APPLE_STROKE_WEIGHT = 1;

final color SNAKE_COLOR = color(255);
final color DEAD_SNAKE_COLOR = color(255, 0, 0);
final color SNAKE_STROKE_COLOR = color(0);
final color DEAD_SNAKE_STROKE_COLOR = color(0);
final float SNAKE_STROKE_WEIGHT = 1;
          
final int INITIAL_SNAKE_LENGTH = 20;

final color COOLDOWN_OVERLAY_COLOR = color(255, 0, 0);
final float COOLDOWN_SPEED = 10.0f;

float horizontalFieldShift,
      verticalFieldShift;

float cellSize;

int[][] availablePositions;

int appleX,
    appleY;

int[] snakeXs,
      snakeYs;
int snakeDX,
    snakeDY;
int snakeLength;

boolean hasStarted = true;
float invincibilityTime = 3;

boolean hasLost = false;
float cooldownTime = 0;

void setup() {
  size(1280, 720);
  frameRate(20);

  createGame();
}

void draw() {
  // ToDo
}

/* --- */

void createGame() {
  createField();
  createSnake();
  createApple();  
}

void createField() {
  cellSize = min(width / FIELD_WIDTH, height / FIELD_HEIGHT);
  
  horizontalFieldShift = (width - FIELD_WIDTH * cellSize) * 0.5;
  verticalFieldShift = (height - FIELD_HEIGHT * cellSize) * 0.5;
  
  availablePositions = new int[FIELD_HEIGHT * FIELD_WIDTH][2];
}

void createSnake() {
  snakeXs = new int[FIELD_HEIGHT * FIELD_WIDTH];
  snakeYs = new int[FIELD_HEIGHT * FIELD_WIDTH];

  snakeDX = 1;
  snakeDY = 0;

  snakeLength = INITIAL_SNAKE_LENGTH;
}

void createApple() {
  int i = 0;
  for (int y = 0; y < FIELD_HEIGHT; ++y) {
    for (int x = 0; x < FIELD_WIDTH; ++x) {
      boolean available = true;
      for (int j = 0; available && j < snakeLength; ++j) {
        available = !(snakeXs[j] == x && snakeYs[j] == y);
      }
      
      if (available) {
        availablePositions[i][0] = x;
        availablePositions[i][1] = y;

        ++i;
      }
    }
  }
  
  int randomIndex = int(random(i));
  int[] randomPosition = availablePositions[randomIndex];

  appleX = randomPosition[0];
  appleY = randomPosition[1];
}

void drawField() {
  float fieldX = horizontalFieldShift;
  float fieldY = verticalFieldShift;
  float fieldWidth = FIELD_WIDTH * cellSize;
  float fieldHeight = FIELD_HEIGHT * cellSize;

  fill(FIELD_COLOR);
  stroke(FIELD_STROKE_COLOR);
  strokeWeight(FIELD_STROKE_WEIGHT);
  rect(fieldX, fieldY, fieldWidth, fieldHeight);

  stroke(FIELD_GRID_COLOR);
  strokeWeight(FIELD_GRID_WEIGHT);
  
  for (int x = 0; x < FIELD_WIDTH; ++x) {
    float lineStartX = horizontalFieldShift + x * cellSize;
    float lineStartY = verticalFieldShift;
    float lineEndX = lineStartX;
    float lineEndY = lineStartY + fieldHeight;
    
    line(lineStartX, lineStartY, lineEndX, lineEndY);
  }
  for (int y = 0; y < FIELD_HEIGHT; ++y) {
    float lineStartX = horizontalFieldShift;
    float lineStartY = verticalFieldShift + y * cellSize;
    float lineEndX = lineStartX + fieldWidth;
    float lineEndY = lineStartY;

    line(lineStartX, lineStartY, lineEndX, lineEndY);
  }
}

void drawSnake() {
  for (int i = 0; i < (hasLost ? snakeLength - 1 : snakeLength); ++i) {
    float x = snakeXs[i];
    float y = snakeYs[i];
    float cellX = horizontalFieldShift + x * cellSize;
    float cellY = verticalFieldShift + y * cellSize;

    if (hasLost) {
      fill(DEAD_SNAKE_COLOR, cooldownTime);
      stroke(DEAD_SNAKE_STROKE_COLOR, cooldownTime);
    } else {
      fill(SNAKE_COLOR);
      stroke(SNAKE_STROKE_COLOR);  
    }
    strokeWeight(SNAKE_STROKE_WEIGHT);
    rect(cellX, cellY, cellSize, cellSize);
  }
}

void drawApple() {
  float cellX = horizontalFieldShift + appleX * cellSize;
  float cellY = verticalFieldShift + appleY * cellSize;

  fill(APPLE_COLOR);
  stroke(APPLE_STROKE_COLOR);
  strokeWeight(APPLE_STROKE_WEIGHT);
  rect(cellX, cellY, cellSize, cellSize);
}

void drawCooldownOverlay() {
  fill(COOLDOWN_OVERLAY_COLOR, cooldownTime);
  noStroke();
  rect(0, 0, width, height);
}

void turnSnakeUp() {
  if (snakeDX == 0 && snakeDY == 1) { 
    reverseSnake();
  }

  snakeDX =  0;
  snakeDY = -1;
}

void turnSnakeDown() {
  if (snakeDX == 0 && snakeDY == -1) {
    reverseSnake();
  }

  snakeDX = 0;
  snakeDY = 1;
}

void turnSnakeLeft() {
  if (snakeDX == 1 && snakeDY == 0) {
    reverseSnake();
  }

  snakeDX = -1;
  snakeDY =  0;
}

void turnSnakeRight() {
  if (snakeDX == -1 && snakeDY == 0) {
    reverseSnake();
  }

  snakeDX = 1;
  snakeDY = 0;
}

boolean hasSnakeReachedApple() {
  int head = snakeLength - 1;
  
  int nextX = snakeXs[head] + snakeDX;
  int nextY = snakeYs[head] + snakeDY;
  
  return nextX == appleX && nextY == appleY;
}

void moveSnake() {
  int head = snakeLength - 1; //<>//

  for (int i = 0; i < head; ++i) {
    snakeXs[i] = snakeXs[i + 1];
    snakeYs[i] = snakeYs[i + 1];
  }

  snakeXs[head] += snakeDX;
  if (snakeXs[head] < 0) {
    snakeXs[head] = FIELD_WIDTH + snakeXs[head];  
  }
  if (snakeXs[head] >= FIELD_WIDTH) {
    snakeXs[head] = snakeXs[head] - FIELD_WIDTH;  
  }
  
  snakeYs[head] += snakeDY;
  if (snakeYs[head] < 0) {
    snakeYs[head] = FIELD_HEIGHT + snakeYs[head];  
  }
  if (snakeYs[head] >= FIELD_HEIGHT) {
    snakeYs[head] = snakeYs[head] - FIELD_HEIGHT;  
  }
}

boolean hasSnakeCollidedWithTail() {
  int head = snakeLength - 1;

  for (int i = 0; i < head; ++i) {
    if (snakeXs[i] == snakeXs[head] && snakeYs[i] == snakeYs[head]) {
      return true;
    }
  }
  
  return false;
}

void makeSnakeEatApple() {
  int head = snakeLength;
  
  snakeXs[head] = appleX;
  snakeYs[head] = appleY;

  ++snakeLength;
}

void reverseSnake() {
  for (int i = 0, middle = snakeLength / 2, end = snakeLength - 1, temp = 0; i != middle; ++i) {
    temp = snakeXs[i];
    snakeXs[i] = snakeXs[end - i];
    snakeXs[end - i] = temp;

    temp = snakeYs[i];
    snakeYs[i] = snakeYs[end - i];
    snakeYs[end - i] = temp;
  }
}

void cooldown() {
  if (cooldownTime <= 0) {
    hasLost = false;
    createGame();
  } else {
    drawCooldownOverlay();
    cooldownTime -= COOLDOWN_SPEED;
  }
}

void lose() {
  hasLost = true;
  cooldownTime = 255;
}