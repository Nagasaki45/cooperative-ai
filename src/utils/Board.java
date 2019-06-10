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

    public static Types.TILETYPE[][] simpleBoard()
    {
        return parseString(
                "xxxxxxxxxxx\n" +
                "x         x\n" +
                "x         x\n" +
                "x         x\n" +
                "x         x\n" +
                "x    x    x\n" +
                "x         x\n" +
                "x         x\n" +
                "x         x\n" +
                "x         x\n" +
                "xxxxxxxxxxx\n"
        );
    }
}
