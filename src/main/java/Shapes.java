import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.scene3d.Prism;
import com.almasb.fxgl.scene3d.Torus;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

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
        TransformComponent transform = camera.getTransform();
        transform.translateZ(-10);
        transform.translateY(0);

        getGameScene().setBackgroundColor(Color.LIGHTBLUE);
        getGameScene().setFPSCamera(true);
        getGameScene().setCursorInvisible();

        Node[] shapes = makeShapes();

        int x = 0;

        for (Node shape : shapes) {
            Entity e = makeEntity(x*2-8,0,6);
            e.getViewComponent().addChild(shape);
            x++;
        }
    }

    private Node[] makeShapes(){
        return new Node[]{
                new Box(),
                new Sphere(),
                new Torus(),
                new Prism(1,0.5,2,3),
                new Prism(),
                new Prism(1,1,2,4),
                new Prism(1,1,2,5),
                new Prism(1,1,2,6),
                new Cylinder()
        };
    }

    private Entity makeEntity(double x, double y, double z) {
        return FXGL.entityBuilder()
                .at(x,y,z)
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}