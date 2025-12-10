import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.TransformComponent;
import javafx.scene.Node;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Shapes extends GameApplication {

    private Camera3D camera;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1200);
        settings.setHeight(720);
        settings.set3D(true);
        settings.setTitle("3D Shapes");
    }

    @Override
    protected void initInput() {
        super.initInput();
    }

    @Override
    protected void initGame() {
        camera = getGameScene().getCamera3D();
        TransformComponent transfom = camera.getTransform();
    }

    private Node[] makeShapes(){
        return null;
    }

    private Entity makeEntity(double x, double y, double z) {
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}