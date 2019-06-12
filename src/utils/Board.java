package utils;


public class Board {
    public static Types.TILETYPE[][] parseString(String string)
    {
        String[] lines = string.split("\\r?\\n");
        int rows = lines.length;
        int cols = lines[0].length();
        Types.TILETYPE[][] board = new Types.TILETYPE[rows][cols];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                switch (lines[i].charAt(j)) {
                    case 'x':
                        board[i][j] = Types.TILETYPE.RIGID;
                        break;
                    case ' ':
                        board[i][j] = Types.TILETYPE.PASSAGE;
                        break;
                    default:
                        System.out.println("Invalid tile type!");
                        break;
                }
            }
        }
        return board;
    }

    public static String toString(Types.TILETYPE[][] board)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length+2; i++) {
            stringBuilder.append("*");
        }
        stringBuilder.append("\n");
        for (Types.TILETYPE[] gameObjects : board) {
            stringBuilder.append("*");
            for (Types.TILETYPE type : gameObjects) {
                if (type.getKey() < Types.TILETYPE.AGENT0.getKey() && type.getKey() > 0)
                    stringBuilder.append(type.getKey());
                else {
                    if (type == Types.TILETYPE.PASSAGE)
                        stringBuilder.append(" ");
                    else if (type == Types.TILETYPE.AGENT0)
                        stringBuilder.append("a");
                    else if (type == Types.TILETYPE.AGENT1)
                        stringBuilder.append("b");
                    else
                        stringBuilder.append("-");
                }
            }

            stringBuilder.append("*\n");
        }
        for (int i = 0; i < board.length + 2; i++) {
            stringBuilder.append("*");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public static Types.TILETYPE[][] simpleBoard()
    {
        return parseString(
//                "xxxxxxxxxxx\n" +
//                "x         x\n" +
//                "x         x\n" +
//                "x         x\n" +
//                "x         x\n" +
//                "x    x    x\n" +
//                "x         x\n" +
//                "x         x\n" +
//                "x         x\n" +
//                "x         x\n" +
//                "xxxxxxxxxxx\n"
//                  "xxxxxxxxxxx\n" +
//                  "x         x\n" +
//                  "x         x\n" +
//                  "x         x\n" +
//                  "x         x\n" +
//                  "xxxxx xxxxx\n" +
//                  "x         x\n" +
//                  "x         x\n" +
//                  "x         x\n" +
//                  "x         x\n" +
//                  "xxxxxxxxxxx\n"
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xx xxxxxxxx\n" +
//                      "xx       xx\n" +
//                      "xxxxxxxx xx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxx   xxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
                      "xxxxxxxxxxx\n" +
                      "x         x\n" +
                      "x         x\n" +
                      "xxxxx xxxxx\n" +
                      "xxxxx xxxxx\n" +
                      "xxxxx xxxxx\n" +
                      "xxxxx xxxxx\n" +
                      "xxxxx xxxxx\n" +
                      "x         x\n" +
                      "x         x\n" +
                      "xxxxxxxxxxx\n"
        );
    }
}


//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxx xxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
//
//
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
//
//
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxx xxx xxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
//
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xx xxxxxxxx\n" +
//                      "xx       xx\n" +
//                      "xxxxxxxx xx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
//
//
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxx   xxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"
//
//
//                      "xxxxxxxxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxx xxxxx\n" +
//                      "xxxx   xxxx\n" +
//                      "xxxx   xxxx\n" +
//                      "xxxx   xxxx\n" +
//                      "xxxxx xxxxx\n" +
//                      "x         x\n" +
//                      "x         x\n" +
//                      "xxxxxxxxxxx\n"