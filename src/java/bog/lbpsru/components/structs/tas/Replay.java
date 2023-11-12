package bog.lbpsru.components.structs.tas;

import bog.lbpsru.components.structs.PlayerInputs;
import bog.lbpsru.components.utils.Button;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author Bog
 */
public class Replay {
    public ArrayList<ArrayList<TasInput>> player1;
    public ArrayList<ArrayList<TasInput>> player2;
    public ArrayList<ArrayList<TasInput>> player3;
    public ArrayList<ArrayList<TasInput>> player4;

    public Replay()
    {
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        player3 = new ArrayList<>();
        player4 = new ArrayList<>();
    }

    public static Replay fromJson(File file, JPanel form) throws FileNotFoundException {
        return fromJson(JsonParser.parseReader(new FileReader(file)).getAsJsonObject(), form);
    }

    public static Replay fromJson(JsonObject jsonFile, JPanel form){
        float version = jsonFile.get("srutil-tas-rev").getAsFloat();

        if(version > 1.0f)
        {
            JOptionPane.showMessageDialog(form, "Replay format revision incompatible, for newer SRUtil.", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        else
            if(version < 1.0f)
            {
                JOptionPane.showMessageDialog(form, "Replay format revision outdated, for past SRUtil.", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        JsonArray player1 = jsonFile.getAsJsonArray("player1");
        JsonArray player2 = jsonFile.getAsJsonArray("player2");
        JsonArray player3 = jsonFile.getAsJsonArray("player3");
        JsonArray player4 = jsonFile.getAsJsonArray("player4");

        Replay replay = new Replay();

        TasInput lastInput = new TasInput();

        ArrayList<TasInput> inputs = new ArrayList<>();

        if(player1 != null)
            for (int i = 0; i < player1.size(); i++)
            {
                TasInput input = TasInput.deserialize(player1.get(i), lastInput).clone();

                if(input.wait != null && input.wait.equalsIgnoreCase("level"))
                {
                    replay.player1.add(inputs);
                    inputs = new ArrayList<>();
                }
                else
                    inputs.add(input);
            }
        replay.player1.add(inputs);
        inputs = new ArrayList<>();

        lastInput = new TasInput();

        if(player2 != null)
            for (int i = 0; i < player2.size(); i++)
            {
                TasInput input = TasInput.deserialize(player2.get(i), lastInput).clone();

                if(input.wait != null && input.wait.equalsIgnoreCase("level"))
                {
                    replay.player2.add(inputs);
                    inputs = new ArrayList<>();
                }
                else
                    inputs.add(input);
            }
        replay.player2.add(inputs);
        inputs = new ArrayList<>();

        lastInput = new TasInput();

        if(player3 != null)
            for (int i = 0; i < player3.size(); i++)
            {
                TasInput input = TasInput.deserialize(player3.get(i), lastInput).clone();

                if(input.wait != null && input.wait.equalsIgnoreCase("level"))
                {
                    replay.player3.add(inputs);
                    inputs = new ArrayList<>();
                }
                else
                    inputs.add(input);
            }
        replay.player3.add(inputs);
        inputs = new ArrayList<>();

        lastInput = new TasInput();

        if(player4 != null)
            for (int i = 0; i < player4.size(); i++)
            {
                TasInput input = TasInput.deserialize(player4.get(i), lastInput).clone();

                if(input.wait != null && input.wait.equalsIgnoreCase("level"))
                {
                    replay.player4.add(inputs);
                    inputs = new ArrayList<>();
                }
                else
                    inputs.add(input);
            }
        replay.player4.add(inputs);
        inputs = new ArrayList<>();

        return replay;
    }

    public JsonObject toJson()
    {
        JsonObject json = new JsonObject();

        json.addProperty("srutil-tas-rev", 1.0);

        JsonArray player1 = new JsonArray();
        for(int i = 0; i < this.player1.size(); i++)
        {
            if(i != 0)
                player1.add(new TasInput("level").serialize(0));

            int prevFrame = 0;
            for(TasInput input : this.player1.get(i))
            {
                JsonObject inp = input.serialize(prevFrame);
                prevFrame += inp.get("frame").getAsInt();
                player1.add(inp);
            }
        }
        json.add("player1", player1);

        JsonArray player2 = new JsonArray();
        for(int i = 0; i < this.player2.size(); i++)
        {
            if(i != 0)
                player2.add(new TasInput("level").serialize(0));

            int prevFrame = 0;
            for(TasInput input : this.player2.get(i))
            {
                JsonObject inp = input.serialize(prevFrame);
                prevFrame += inp.get("frame").getAsInt();
                player2.add(inp);
            }
        }
        json.add("player2", player2);

        JsonArray player3 = new JsonArray();
        for(int i = 0; i < this.player3.size(); i++)
        {
            if(i != 0)
                player3.add(new TasInput("level").serialize(0));

            int prevFrame = 0;
            for(TasInput input : this.player3.get(i))
            {
                JsonObject inp = input.serialize(prevFrame);
                prevFrame += inp.get("frame").getAsInt();
                player3.add(inp);
            }
        }
        json.add("player3", player3);

        JsonArray player4 = new JsonArray();
        for(int i = 0; i < this.player4.size(); i++)
        {
            if(i != 0)
                player4.add(new TasInput("level").serialize(0));

            int prevFrame = 0;
            for(TasInput input : this.player4.get(i))
            {
                JsonObject inp = input.serialize(prevFrame);
                prevFrame += inp.get("frame").getAsInt();
                player4.add(inp);
            }
        }
        json.add("player4", player4);

        return json;
    }
}
